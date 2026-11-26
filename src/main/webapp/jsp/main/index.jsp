<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>DDANGO | 그거 말고 딴거</title>

    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700;800;900&family=Noto+Sans+KR:wght@400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- 공통 CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/index.css">

    <!-- 로그인 페이지 CSS -->
    <c:if test="${content == '/jsp/main/login.jsp'}">

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/signup.css">

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/login.css">

    </c:if>

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

            <a href="${pageContext.request.contextPath}/jsp/book/bookSearch.jsp">
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

                    <a href="${pageContext.request.contextPath}/mypage"
                       class="mypage-link">
                        마이페이지
                    </a>

                    <form action="${pageContext.request.contextPath}/logout"
                          method="post" class="logout-form">

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
    const isNotLogin = ${empty sessionScope.loginUser};
    const contextPath = "${pageContext.request.contextPath}";
</script>

<script src="${pageContext.request.contextPath}/js/main/index.js"></script>

</body>
</html>

</body>
</html>

</body>


</html>
