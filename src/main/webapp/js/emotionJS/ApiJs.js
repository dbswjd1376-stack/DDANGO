const music = document.getElementById("music");
const tracks = document.getElementById("music-tracks");
const statusText = document.getElementById("music-status");
const player = document.getElementById("music-player");

// API 요청 function
async function fetchMusic(path, params) {
    const url = music.dataset.api + path + "?" + new URLSearchParams(params);
    const response = await fetch(url);

    if (!response.ok) {
        throw new Error("요청 실패 (HTTP " + response.status + ")");
    }

    const data = await response.json();
    if (data.error) {
        throw new Error(data.message || "API 요청에 실패했어요.");
    }

    return data;
}

// last.fm track 목록 받는 API 값 function
async function loadVideo(track) {
    statusText.textContent = "YouTube 영상을 찾는 중…";

    try {
        const query = track.artist.name + " " + track.name;
        const data = await fetchMusic("/video", { q: query });
        const videoId = data.items?.[0]?.id?.videoId;

        // VideoId 값 없을 경우 Error
        if (!videoId) {
            throw new Error("재생 가능한 영상을 찾지 못했어요.");
        }

        player.src = "https://www.youtube.com/embed/" + encodeURIComponent(videoId);
        player.hidden = false;
        statusText.textContent = "플레이어에서 ▶를 눌러 주세요.";
    } catch (error) {
        statusText.textContent = error.message;
    }
}

// track 눌렀을 경우 영상 load function
async function loadTracks(mood) {
    tracks.replaceChildren();
    statusText.textContent = "추천곡을 불러오는 중…";

    try {
        const data = await fetchMusic("/recommend", { mood });
        const recommendations = data.tracks?.track ?? [];

        for (const track of recommendations) {
            const item = document.createElement("li");
            const button = document.createElement("button");
            button.type = "button";
            button.textContent = track.artist.name + " - " + track.name + " 듣기";
            button.addEventListener("click", () => loadVideo(track));

            item.appendChild(button);
            tracks.appendChild(item);
        }

        statusText.textContent = recommendations.length ? "" : "추천곡이 없어요.";
    } catch (error) {
        statusText.textContent = error.message;
    }
}

// data-tag 받아서 넘겨주는 query
music.querySelectorAll("[data-mood]").forEach((button) => {
    button.addEventListener("click", () => loadTracks(button.dataset.mood));
});