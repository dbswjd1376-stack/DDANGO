<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DDANGO | 그거 말고 딴거</title>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700;800;900&family=Noto+Sans+KR:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">

</head>

<body>

<header>
    <div class="header-inner">

        <a href="${pageContext.request.contextPath}/jsp/main/index.jsp" class="logo">
            DDANGO<span>딴거</span>
        </a>

        <nav>
            <a href="${pageContext.request.contextPath}/jsp/main/index.jsp">DISCOVER</a>
            <a href="${pageContext.request.contextPath}/emotion/emotionMain.jsp">감정별 노래추천</a>
            <a href="#" onclick="return comingSoon(event)">도서로 노래추천</a>
        </nav>

        <div class="header-auth">

            <c:choose>

                <%-- 비로그인 상태 --%>
                <c:when test="${empty sessionScope.loginUser}">

                    <a href="${pageContext.request.contextPath}/signup" class="header-signup">
                        회원가입
                    </a>

                    <a href="${pageContext.request.contextPath}/login" class="login">
                        로그인 ↗
                    </a>

                </c:when>

                <%-- 로그인 상태 --%>
                <c:otherwise>

                    <span class="header-nickname">
                        <c:out value="${sessionScope.loginUser.u_nickname}"/>님
                    </span>

                    <form action="${pageContext.request.contextPath}/logout" method="post" class="logout-form">

                        <button type="submit" class="logout">
                            로그아웃 ↗
                        </button>

                    </form>

                </c:otherwise>

            </c:choose>

        </div>

    </div>
</header>
<main>
    <section class="hero">
        <div>
            <span class="kicker">NOT THAT ONE. THIS ONE.</span>
            <h1>그거 말고,<br><em>딴거.</em></h1>
            <p class="hero-description">
                맨날 듣던 노래 말고, 새로운 취향.<br>
                내 기분에 맞는 음악부터 책 속의 한 장면까지.<br>
                오늘의 딴거를 발견해 보세요.
            </p>

            <div class="hero-actions">
                <button class="primary" onclick="scrollToPlaylists()">
                    플리 구경하기 ↗
                </button>
                <button onclick="comingSoon()">
                    내 플리 만들기
                </button>
            </div>
        </div>

        <div class="record-art">
            <div class="record">
                <div class="record-center">딴거!</div>
            </div>
            <span class="record-sticker">NEW<br>MOOD!</span>
        </div>
    </section>

    <div class="marquee">
        <div class="marquee-track">
            <span>DDANGO ★ DIFFERENT MUSIC ★ NEW MOOD ★ NOT THAT ONE ★ </span>
            <span>DDANGO ★ DIFFERENT MUSIC ★ NEW MOOD ★ NOT THAT ONE ★ </span>
        </div>
    </div>

    <!-- 개발자 플리 -->
    <section class="music-section" id="playlists">
        <div class="section-top">
            <div>
                <span class="section-number">01 / EDITOR'S PICK</span>
                <h2>우리가 먼저 들어봤어.</h2>
                <p>개발자가 직접 고른, 오래 듣고 싶은 음악들.</p>
            </div>
            <button class="more" onclick="comingSoon()">모두 보기 ↗</button>
        </div>

        <div class="playlist-grid">
            <article class="playlist featured"
                     data-title="퇴근길, 애매한 기분">
                <div class="cover cover-peach">
                    <span class="tag">EDITOR'S PICK</span>
                    <strong class="cover-word">오늘은<br>그냥.</strong>
                    <span class="cover-bottom">VOL. 001 / 12 TRACKS</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>퇴근길, 애매한 기분</h3>
                <p>인디 · 어쿠스틱 · 12곡</p>
            </article>

            <article class="playlist"
                     data-title="비 오는 날 창가">
                <div class="cover cover-blue">
                    <span class="tag">EDITOR'S PICK</span>
                    <strong class="cover-word">비의<br>온도</strong>
                    <span class="cover-bottom">VOL. 002</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>비 오는 날 창가</h3>
                <p>로파이 · 15곡</p>
            </article>

            <article class="playlist"
                     data-title="새벽 세 시의 잔상">
                <div class="cover cover-purple">
                    <span class="tag">EDITOR'S PICK</span>
                    <strong class="cover-word">03:00<br>AM</strong>
                    <span class="cover-bottom">VOL. 003</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>새벽 세 시의 잔상</h3>
                <p>R&B · 14곡</p>
            </article>
        </div>
    </section>

    <!-- 플리 만들기 -->
    <section class="share-banner">
        <div>
            <span class="section-number">MAKE YOUR OWN MIX</span>
            <h2>네 취향도 들려줄래?</h2>
            <p>좋아하는 노래를 담고, 누군가의 새로운 딴거가 되어줘.</p>
        </div>
        <button onclick="comingSoon()">내 플레이리스트 만들기 ↗</button>
    </section>

    <!-- 유저 플리 -->
    <section class="music-section">
        <div class="section-top">
            <div>
                <span class="section-number">02 / FROM OUR PEOPLE</span>
                <h2>남의 취향도 꽤 괜찮아.</h2>
                <p>누군가의 하루가 담긴 플레이리스트.</p>
            </div>
            <button class="more" onclick="comingSoon()">모두 보기 ↗</button>
        </div>

        <div class="playlist-grid">
            <article class="playlist" data-title="혼자 걷는 밤 산책">
                <div class="cover cover-red">
                    <span class="tag">USER MIX</span>
                    <strong class="cover-word">혼자<br>걷기</strong>
                    <span class="cover-bottom">BY @밤산책</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>혼자 걷는 밤 산책</h3>
                <p>@밤산책 · 9곡</p>
            </article>

            <article class="playlist" data-title="시험 끝나고 첫 주말">
                <div class="cover cover-yellow">
                    <span class="tag">USER MIX</span>
                    <strong class="cover-word">드디어<br>끝!</strong>
                    <span class="cover-bottom">BY @금요일</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>시험 끝나고 첫 주말</h3>
                <p>@금요일 · 18곡</p>
            </article>

            <article class="playlist featured"
                     data-title="카페에서 책 읽을 때">
                <div class="cover cover-mint">
                    <span class="tag">USER MIX</span>
                    <strong class="cover-word">책 한 권,<br>노래 한 곡.</strong>
                    <span class="cover-bottom">BY @책갈피</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>카페에서 책 읽을 때</h3>
                <p>@책갈피 · 13곡</p>
            </article>
        </div>
    </section>

    <!-- AI 자작곡 -->
    <section class="music-section">
        <div class="section-top">
            <div>
                <span class="section-number">03 / NEW SOUND LAB</span>
                <h2>세상에 없던 소리.</h2>
                <p>AI로 만든 새로운 자작곡 플레이리스트.</p>
            </div>
            <button class="more" onclick="comingSoon()">모두 보기 ↗</button>
        </div>

        <div class="playlist-grid">
            <article class="playlist featured"
                     data-title="말 없는 위로">
                <div class="cover cover-dark">
                    <span class="tag">AI ORIGINAL</span>
                    <strong class="cover-word">괜찮지<br>않아도.</strong>
                    <span class="cover-bottom">SOUND LAB / 01</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>말 없는 위로</h3>
                <p>AI 자작곡 · 7곡</p>
            </article>

            <article class="playlist"
                     data-title="여름밤 옥상">
                <div class="cover cover-purple">
                    <span class="tag">AI ORIGINAL</span>
                    <strong class="cover-word">여름의<br>끝</strong>
                    <span class="cover-bottom">SOUND LAB / 02</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>여름밤 옥상</h3>
                <p>AI 자작곡 · 10곡</p>
            </article>

            <article class="playlist"
                     data-title="잔잔한 소설 한 챕터">
                <div class="cover cover-green">
                    <span class="tag">AI ORIGINAL</span>
                    <strong class="cover-word">다음<br>페이지</strong>
                    <span class="cover-bottom">SOUND LAB / 03</span>
                    <button class="play-circle" aria-label="플리 보기">▶</button>
                </div>
                <h3>잔잔한 소설 한 챕터</h3>
                <p>AI 자작곡 · 9곡</p>
            </article>
        </div>
    </section>
</main>

<footer>
    <strong>DDANGO 딴거.</strong>
    <span>NOT THAT ONE. THIS ONE. © DDANGO</span>
</footer>

<script>
    function comingSoon(event) {
        if (event) event.preventDefault();
        alert("이 기능은 추후 연결할 예정입니다.");
        return false;
    }

    function scrollToPlaylists() {
        document.querySelector("#playlists").scrollIntoView({
            behavior: "smooth"
        });
    }

    document.querySelectorAll(".play-circle").forEach(button => {
        button.addEventListener("click", function(event) {
            event.stopPropagation();

            const playlist = this.closest(".playlist");
            alert(
                playlist.dataset.title +
                "\n\n플레이리스트 상세페이지는 추후 연결할 예정입니다."
            );
        });
    });
</script>

</body>
</html>