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

        <a href="${pageContext.request.contextPath}/main" class="logo">

            DDANGO<span>딴거</span>
        </a>


        <nav>
            <a href="${pageContext.request.contextPath}/main">
                DISCOVER
            </a>

            <a href="${pageContext.request.contextPath}/emotion/emotionMain.jsp">
                감정별 노래추천
            </a>

            <a href="#" onclick="return comingSoon(event)">
                도서로 노래추천
            </a>
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
    <jsp:include page="${content}" />
</main>

<footer>
    <strong>DDANGO 딴거.</strong>
    <span>NOT THAT ONE. THIS ONE. © DDANGO</span>
</footer>


<script>

    // 1. 로그인 여부 확인
    function checkLogin() {

        if (${empty sessionScope.loginUser}) {

            alert("회원가입 및 로그인 후 이용해 주세요^^");

            location.href = "${pageContext.request.contextPath}/signup";

            return false;
        }

        return true;
    }


    // 2. 메인페이지 전체 버튼 및 링크 클릭 검사
    document.addEventListener("click", function(event) {

        const target = event.target.closest("a, button, .playlist");

        // 버튼이나 링크, 플레이리스트가 아니면 실행하지 않음
        if (target == null) return;

        // 회원가입과 로그인 링크는 정상적으로 이용 가능
        if (target.matches(".header-signup, a.login")) return;

        // 비로그인 사용자 접근 제한
        // if (!checkLogin()) {
        //     event.preventDefault();
        //     event.stopImmediatePropagation();
        // }

    }, true);


    // 3. 아직 구현되지 않은 기능
    function comingSoon(event) {

        if (event) event.preventDefault();

        alert("이 기능은 추후 연결할 예정입니다.");

        return false;
    }


    // 4. 플레이리스트 구경하기
    function scrollToPlaylists() {

        document.querySelector("#playlists").scrollIntoView({
            behavior: "smooth"
        });

    }


    // 5. 플레이리스트 상세보기
    document.querySelectorAll(".playlist").forEach(playlist => {

        playlist.addEventListener("click", function(event) {

            event.stopPropagation();

            alert(
                this.dataset.title +
                "\n\n플레이리스트 상세페이지는 추후 연결할 예정입니다."
            );

        });

    });

</script>

</body>


</html>
