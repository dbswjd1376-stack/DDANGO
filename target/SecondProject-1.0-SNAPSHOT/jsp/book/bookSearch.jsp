<!--도서 제목과 문구 입력-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>도서 검색</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/booksearch.css">
</head>

<body>
<h1>BOOK PLAYLIST</h1>
<p>책 한 권에서 시작되는 나만의 음악</p>
<div class="journey">
    <h2>YOUR BOOk JOURNEY</h2>

    <p id="journeyText">
        책 선택 전  →  전체 플리
    </p>
</div>
<form id="searchForm"
      action="${pageContext.request.contextPath}/book-search"
      method="get">

    <h3>도서 제목</h3>
    <input type="text" name="keyword" id="bookTitle"
           placeholder="도서 제목을 입력해주세요."
           required>

    <h3>도서 문구</h3>
    <textarea name="quote"
              placeholder="마음에 남은 문장을 입력해주세요."></textarea>

    <hr>

    <h3>어떤 방식으로 추천받을까요?</h3>

    <input type="hidden" name="mode" id="mode" value="all">

    <div class="mode-buttons">
        <button type="button"
                class="mode-btn active"
                onclick="selectMode('all', this)">
            <strong>플리 추천</strong>
            <small>책 전체의 분위기에 어울리는 플레이리스트</small>
        </button>

        <button type="button"
                class="mode-btn"
                onclick="selectMode('chapter', this)">
            <strong>챕터별 맞춤 플리</strong>
            <small>챕터마다 달라지는 분위기에 맞춘 플레이리스트</small>
        </button>
    </div>

    <div class="search-buttons">
    <button type="submit">찾기</button>
    <button type="button" onclick="resetSearch()">초기화</button>
    </div>
</form>

<div id="searchResult"></div>

<script src="${pageContext.request.contextPath}/js/book/booksearch.js"></script>
</body>
</html>
