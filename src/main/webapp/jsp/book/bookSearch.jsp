<!--도서 제목과 문구 입력-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>도서 검색</title>
</head>
<style>
    .mode-buttons {
        display: flex;
        gap: 10px;
    }

    .mode-btn {
        flex: 1;
        padding: 15px;
        text-align: left;

        border: 1px solid rgba(255,255,255,0.15);
        border-radius: 15px;

        background: rgba(255,255,255,0.07);
        color: white;

        cursor: pointer;
        transition: all 0.2s ease;
    }

    /* 선택되지 않은 버튼 */
    .mode-btn:not(.active) {
        opacity: 0.65;
    }

    /* 선택된 버튼 */
    .mode-btn.active {
        background: linear-gradient(
                135deg,
                rgba(255, 130, 190, 0.35),
                rgba(170, 110, 255, 0.35)
        );

        border: 1.5px solid #f5c8ff;

        box-shadow: 0 0 12px rgba(220, 130, 255, 0.25);

        opacity: 1;
        transform: translateY(-2px);
    }

    .mode-btn strong {
        display: block;
        margin-bottom: 8px;
    }

    .mode-btn small {
        color: #d8cde0;
    }
</style>




<body>
<h1>BOOK PLAYLIST</h1>
<p>책 한 권에서 시작되는 나만의 음악</p>

<form action="${pageContext.request.contextPath}/book-search" method="get">
    <h3>도서 제목 </h3>
    <input type="text" name="keyword"
           placeholder="도서 제목을 입력해주세요."
           required>
<%--    입력 필수--%>

    <h3>도서 문구</h3>
    <textarea name="quote"
              placeholder="마음에 남은 문장을 입력해주세요."></textarea>
<%--입력 선택--%>

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

    <button type="submit">찾기</button>
    <button type="submit">test</button>

</form>
<script>
    function selectMode(mode, button) {

        // 선택한 추천 방식 저장
        document.getElementById("mode").value = mode;

        // 모든 버튼의 선택 효과 제거
        const buttons = document.querySelectorAll(".mode-btn");

        buttons.forEach(function(btn) {
            btn.classList.remove("active");
        });

        // 선택한 버튼만 강조
        button.classList.add("active");
    }
</script>
</body>
</html>
