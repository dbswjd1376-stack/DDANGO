const music = document.getElementById("music");
const tracks = document.getElementById("music-tracks");
const statusText = document.getElementById("music-status");
const player = document.getElementById("music-player");
const playlists = document.getElementById("music-playlists");
const playlistStatus = document.getElementById("playlist-status");

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
async function loadTracks(moodCode, genreCode) {
    tracks.replaceChildren();
    statusText.textContent = "Last.fm 추천곡을 불러오는 중…";

    try {
        // Java가 세부 감정 코드를 Last.fm 태그로 바꿔서 검색
        // Servlet에 감정과 장르를 각각 요청
// 예: mood=unrequited-love → sad, genre=jpop → j-pop
        const [moodData, genreData] = await Promise.all([
            fetchMusic("/recommend", { mood: moodCode }),
            fetchMusic("/recommend", { genre: genreCode })
        ]);

        const moodSongs = moodData.tracks?.track ?? [];
        const genreSongs = genreData.tracks?.track ?? [];

// 장르 목록의 곡 주소를 모아 감정 목록에서도 같은 곡을 찾음
        // 감정 인기곡 목록에 들어 있는 곡 주소를 모아
        const moodTrackUrls = new Set(
            moodSongs.map((song) => song.url).filter(Boolean)
        );

// 선택 장르 안에서 감정 목록에도 있는 곡을 먼저 보기
        const matchedSongs = genreSongs.filter((song) => moodTrackUrls.has(song.url));
        const otherGenreSongs = genreSongs.filter((song) => !moodTrackUrls.has(song.url));
        const songs = [...matchedSongs, ...otherGenreSongs];
        for (const [index, song] of songs.entries()) {
            const item = document.createElement("li");
            const card = document.createElement("button");
            card.type = "button";

            // 이미지가 있으면 large 크기를 우선 사용
            const imageUrl = song.image?.find(
                (image) => image.size === "large"
            )?.["#text"];

            if (imageUrl?.startsWith("https://")) {
                const image = document.createElement("img");
                image.src = imageUrl;
                image.alt = "";
                image.width = 96;
                image.height = 96;
                image.loading = "lazy";
                card.appendChild(image);
            }

            const title = document.createElement("strong");
            title.textContent = song.name;

            const artist = document.createElement("span");
            artist.textContent = song.artist.name;

            // 카드를 누른 곡만 YouTube에서 검색
            card.addEventListener("click", () => playFromTrack(songs, index));
            card.append(title, " — ", artist);

            item.appendChild(card);
            tracks.appendChild(item);
        }

        statusText.textContent = songs.length
            ? ""
            : "이 감정과 장르의 인기곡 목록에 공통으로 들어 있는 곡이 없어요.";
    } catch (error) {
        statusText.textContent = "Last.fm 추천 실패 (" + error.message + ")";
    }
    if (!songs.length) {
        statusText.textContent = "선택한 장르의 곡을 가져오지 못했어요.";
    } else if (!matchedSongs.length) {
        statusText.textContent =
            "두 목록에 공통으로 들어 있는 곡이 없어 선택한 장르의 인기곡을 보여드려요.";
    } else {
        statusText.textContent = "감정에도 맞는 곡을 먼저 보여드려요.";
    }
}

async function loadPlaylists(searchText) {
    playlists.replaceChildren();
    playlistStatus.textContent = "플레이리스트를 찾는 중…";

    // 기존 Java Servlet에 조합한 검색어를 q로 보냄
    const data = await fetchMusic("/playlists", {
        q: searchText + " playlist"
    });

    for (const playlist of data.items ?? []) {
        const playlistId = playlist.id?.playlistId;
        if (!playlistId) continue;

        const item = document.createElement("li");
        const button = document.createElement("button");
        button.type = "button";
        button.textContent = playlist.snippet.title;

        // 플리 제목을 누르면 기존 YouTube 플레이어에서 열기
        button.addEventListener("click", () => {
            // 플리 전체를 열면 YouTube 플레이어가 다음 곡을 이어서 재생해요.
            player.src = "https://www.youtube.com/embed?listType=playlist&list="
                + encodeURIComponent(playlistId);
            player.hidden = false;
            playlistStatus.textContent = "▶를 누르면 플리 순서대로 재생돼요.";
        });

        item.appendChild(button);
        playlists.appendChild(item);
    }

    playlistStatus.textContent = playlists.childElementCount
        ? ""
        : "이 조합에 맞는 플레이리스트를 찾지 못했어요.";
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

const moodButtons = [...music.querySelectorAll("button[data-mood]")];
const genreButtons = [...music.querySelectorAll("button[data-genre]")];
const targetMoodButton = document.getElementById("toggle-target-mood");
const targetMoodPanel = document.getElementById("target-mood-panel");
const targetMood = document.getElementById("target-mood");

moodButtons.forEach((button) => {
    // 현재 감정에서 제외한 항목도 목표 감정으로 선택
    const option = new Option(button.textContent.trim(), button.dataset.mood);
    targetMood.add(option);
});

targetMoodButton.addEventListener("click", () => {
    targetMoodPanel.hidden = !targetMoodPanel.hidden;
    targetMoodButton.setAttribute("aria-expanded", String(!targetMoodPanel.hidden));

    if (!targetMoodPanel.hidden) {
        targetMood.focus();
    }
});

// 목표가 바뀌면 이전 추천 목록과 조합 순서를 초기화
targetMood.addEventListener("change", resetRecommendations);

const recommendButton = document.getElementById("recommend-playlists");
const resetButton = document.getElementById("reset-music-filters");
const filterStatus = document.getElementById("music-filter-status");
const playlistQuery = document.getElementById("music-playlist-query");

let nextPairIndex = 0;

function updateRemaining() {
    // hidden이 false인 버튼만 아직 남아 있는 후보
    const moodCount = moodButtons.filter((button) => !button.hidden).length;
    const genreCount = genreButtons.filter((button) => !button.hidden).length;

    filterStatus.textContent =
        "남은 감정 " + moodCount + "개 · 남은 장르 " + genreCount + "개";
}

function resetRecommendations() {
    // 후보가 달라지면 새 조합의 첫 번째부터 추천
    tracks.replaceChildren();
    statusText.textContent = "";
    recommendButton.textContent = "플리 추천받기";
    playlistQuery.textContent = "";
    playlists.replaceChildren();
    playlistStatus.textContent = "";
}

function registerExclusion(buttons, label) {
    buttons.forEach((button) => {
        button.addEventListener("click", () => {
            const remaining = buttons.filter((candidate) => !candidate.hidden);

            // 감정과 장르가 각각 하나 이상 있어야 조합
            if (remaining.length <= 1) {
                filterStatus.textContent = label + "은(는) 하나 이상 남겨 주세요.";
                return;
            }

            button.hidden = true;
            resetRecommendations();
            updateRemaining();
        });
    });
}

registerExclusion(moodButtons, "감정");
registerExclusion(genreButtons, "장르");

resetButton.addEventListener("click", () => {
    [...moodButtons, ...genreButtons].forEach((button) => {
        button.hidden = false;
    });
    
    targetMood.value = "";
    targetMoodPanel.hidden = true;
    targetMoodButton.setAttribute("aria-expanded", "false");

    resetRecommendations();
    updateRemaining();
});

recommendButton.addEventListener("click", () => {
    // 목표 감정을 선택했다면 그 감정으로, 아니면 남은 감정으로 플리 만들기
    const moods = targetMood.value
        ? moodButtons.filter((button) => button.dataset.mood === targetMood.value)
        : moodButtons.filter((button) => !button.hidden);
    const genres = genreButtons.filter((button) => !button.hidden);

    // 같은 장르만 먼저 나열되지 않도록 장르를 번갈아 배치
    const pairs = moods.flatMap((mood, moodIndex) =>
        genres.map((_, index) => ({
            mood,
            genre: genres[(moodIndex + index) % genres.length]
        }))
    ).slice(0, 10);

    playlists.replaceChildren();
    tracks.replaceChildren();
    statusText.textContent = "";
    playlistQuery.textContent = "";

    for (const { mood, genre } of pairs) {
        const title = mood.textContent.trim() + " + " + genre.textContent.trim();
        const item = document.createElement("li");
        const button = document.createElement("button");

        button.type = "button";
        button.textContent = title;
        button.addEventListener("click", () => {
            playlistQuery.textContent = title;
            // 여기서 기존 Java /recommend를 통해 Last.fm 곡을 가져옴
            loadTracks(mood.dataset.mood, genre.dataset.genre);
        });

        item.appendChild(button);
        playlists.appendChild(item);
    }

    playlistStatus.textContent = pairs.length
        ? "플레이리스트를 선택하면 곡 목록이 열려요."
        : "감정과 장르를 하나 이상 남겨 주세요.";
});

updateRemaining();
let playbackRequest = 0;

async function playFromTrack(songs, startIndex) {
    // 다른 곡을 누르면 이전 검색 결과가 플레이어를 덮어쓰지 않게
    const request = ++playbackRequest;
    statusText.textContent = "재생할 영상들을 찾는 중…";

    try {
        const videoIds = [];

        for (const song of songs.slice(startIndex)) {
            if (request !== playbackRequest) return;

            const query = song.artist.name + " " + song.name;
            const data = await fetchMusic("/video", { q: query });
            const videoId = data.items?.[0]?.id?.videoId;

            if (videoId) videoIds.push(videoId);
        }

        if (request !== playbackRequest) return;

        if (!videoIds.length) {
            statusText.textContent = "재생 가능한 영상을 찾지 못했어요.";
            return;
        }

        const [firstVideo, ...nextVideos] = videoIds;
        const params = new URLSearchParams();

        // 첫 곡은 URL에, 이후 곡은 playlist 파라미터에 순서대로
        if (nextVideos.length) {
            params.set("playlist", nextVideos.join(","));
        }

        player.src = "https://www.youtube.com/embed/"
            + encodeURIComponent(firstVideo) + "?" + params.toString();
        player.hidden = false;
        statusText.textContent = "▶를 누르면 준비된 " + videoIds.length + "곡이 순서대로 재생돼요.";
    } catch (error) {
        if (request === playbackRequest) {
            statusText.textContent = "재생 목록을 준비하지 못했어요. " + error.message;
        }
    }
}