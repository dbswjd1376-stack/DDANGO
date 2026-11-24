const music = document.getElementById("music");
const tracks = document.getElementById("music-tracks");
const statusText = document.getElementById("music-status");
const player = document.getElementById("music-player");

async function fetchMusic(path, params) {
    const response = await fetch(
        music.dataset.api + path + "?" + new URLSearchParams(params)
    );
    if (!response.ok) {
        throw new Error("요청 실패 (HTTP" + response.status + ")");
    }

    const data = await response.json();
    if (data.error) {
        throw new Error(data.message || "API 요청에 실패했습니다.");
    }
    return data;
}

async function loadVideo(track) {
    statusText.textContent = "Youtube 영상을 찾는 중...";
    try {
        const query = track.artist.name + " " + track.name;
        const data = await fetchMusic("/video/" + query);
        const videoId = data.items?.[0]?.id?.videoId;

        if (!videoId) {
            throw new Error("재생 가능한 영상을 찾지 못했어요.");
        }

        player.src = "https://www.youtube.com/embed/" + encodeURIComponent(videoId);
        player.hidden = false;
        statusText.textContent = "플레이어에서 재생을 눌러 주세요.";
    } catch (error) {
        statusText.textContent = error.message;
    }
}

async function loadTracks(tag) {
    tracks.replaceChildren();
    statusText.textContent = "추천곡을 불러오는 중...";

    try {
        const data = await fetchMusic("/recommend", { tag });
        const recommenddations = data.tracks?.track ?? [];

        for (const track of recommenddations) {
            const item = document.createElement("li");
            const button = document.createElement("button");
            button.type = "button";
            button.textContent = track.artist.name + " - " + track.name + " 듣기";
            item.appendChild(button);
            tracks.appendChild(item);
        }

        statusText.textContent = recommenddations.length ? "" : "추천곡이 없어요.";

    } catch (error) {
        statusText.textContent = error.message;
    }
}

music.querySelectorAll("[data-tag]").forEach(button => {
    button.addEventListener("click", () => loadTracks(button.dataset.tag));
});