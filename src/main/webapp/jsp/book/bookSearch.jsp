
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DDANGO | 도서로 노래 추천</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/booksearch.css">
</head>

<body>

<!-- 상단 헤더 -->
<header class="header">
    <div class="header-inner">

        <a href="${pageContext.request.contextPath}/index.jsp" class="logo">
            DDANGO <span>●●●●</span>
        </a>

        <nav class="header-nav">
            <a href="#" class="nav-btn">노래추천</a>
            <a href="#" class="nav-btn active">도서로 노래 추천</a>
        </nav>

        <div class="header-account">
            <button type="button" onclick="alert('로그인 기능은 추후 연결 예정입니다.')">
                로그인
            </button>
            <button type="button" class="signup"
                    onclick="alert('회원가입 기능은 추후 연결 예정입니다.')">
                회원가입
            </button>
        </div>

    </div>
</header>


<main class="page-container">

    <!-- 상단 2단 레이아웃 -->
    <div class="top-layout">

        <!-- 왼쪽 소개 영역 -->
        <aside class="intro-area">

            <span class="intro-label">YOUR READING SPACE</span>

            <h1>
                책의 분위기를<br>
                <span>음악으로 듣다.</span>
            </h1>

            <p class="intro-description">
                제목이나 마음에 남은 문장을 적어 주세요.<br>
                이야기의 분위기에 어울리는 음악을 찾아드려요.
            </p>

            <!-- 그라데이션 이미지 영역 -->
            <div class="intro-art"></div>

            <!-- 기존 JOURNEY 기능 유지 -->
            <div class="journey">

                <h2>YOUR BOOK JOURNEY</h2>

                <p id="journeyText">
                    책 선택 전 → 전체 플리
                </p>

            </div>

        </aside>


        <!-- 오른쪽 도서 검색 영역 -->
        <section class="search-panel">

            <form id="searchForm"
                  action="${pageContext.request.contextPath}/book-search"
                  method="get">

                <!-- 01 BOOK -->
                <div class="form-section">

                    <span class="section-label">01 / BOOK</span>

                    <h2>어떤 책을 읽고 있나요?</h2>

                    <p class="section-description">
                        도서 제목을 입력해 주세요. 마음에 남은 문장은 선택이에요.
                    </p>

                    <div class="input-group">

                        <label for="bookTitle">
                            도서 제목 <span>(필수)</span>
                        </label>

                        <input type="text"
                               name="keyword"
                               id="bookTitle"
                               placeholder="도서 제목을 입력해주세요."
                               required>

                    </div>

                    <div class="input-group">

                        <label for="bookQuote">
                            도서 문구 <span>(선택 사항)</span>
                        </label>

                        <textarea name="quote"
                                  id="bookQuote"
                                  placeholder="마음에 남은 문장을 입력해주세요."></textarea>

                    </div>

                </div>


                <hr class="section-divider">


                <!-- 02 MODE -->
                <div class="form-section mode-section">

                    <span class="section-label">02 / MODE</span>

                    <h2>어떤 방식으로 추천받을까요?</h2>

                    <input type="hidden"
                           name="mode"
                           id="mode"
                           value="all">

                    <div class="mode-buttons">

                        <button type="button"
                                class="mode-btn active"
                                onclick="selectMode('all', this)">

                            <strong>플리 추천</strong>

                            <small>
                                책 전체의 분위기에 어울리는 플레이리스트
                            </small>

                        </button>


                        <button type="button"
                                class="mode-btn"
                                onclick="selectMode('chapter', this)">

                            <strong>챕터별 맞춤 플리</strong>

                            <small>
                                챕터마다 달라지는 분위기에 맞춘 플레이리스트
                            </small>

                        </button>

                    </div>

                    <p class="mode-notice">
                        챕터별 맞춤 플리는 챕터 정보가 있는 책에서만 제공돼요.
                    </p>

                </div>


                <!-- 검색 버튼 -->
                <div class="search-buttons">

                    <button type="submit" class="search-submit">
                        찾기
                    </button>

                    <button type="button"
                            class="search-reset"
                            onclick="resetSearch()">
                        선택 초기화
                    </button>

                </div>

            </form>

        </section>

    </div>


    <!-- 검색 결과 -->
    <div id="searchResult"></div>

</main>


<script src="${pageContext.request.contextPath}/js/book/booksearch.js"></script>

</body>
</html>
