<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- 비로그인 사용자 직접 접근 제한 --%>
<c:if test="${empty sessionScope.loginUser}">
    <c:redirect url="/signup"/>
</c:if>

<%-- JSP에 직접 접근한 경우 Controller로 이동 --%>
<c:if test="${empty requestScope.mypageUser and empty requestScope.mypageError}">
    <c:redirect url="/mypage"/>
</c:if>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>마이페이지 | DDANGO</title>

    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700;800;900&family=Noto+Sans+KR:wght@400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">

</head>

<body>

<header>
    <div class="header-inner">

        <a href="${pageContext.request.contextPath}/jsp/main/index.jsp"
           class="logo">
            DDANGO<span>딴거</span>
        </a>

        <nav>
            <a href="${pageContext.request.contextPath}/jsp/main/index.jsp">
                DISCOVER
            </a>

            <a href="${pageContext.request.contextPath}/emotion/emotionMain.jsp">
                감정별 노래추천
            </a>

            <a href="#" onclick="">
                도서로 노래추천
            </a>
        </nav>

        <div class="header-auth">

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

        </div>

    </div>
</header>


<main class="mypage-main">

    <!-- 마이페이지 제목 -->
    <div class="mypage-title">

        <span class="mypage-kicker">
            MY DDANGO ★
        </span>

        <h1>나만의 취향, <em>딴거.</em></h1>

        <p>나의 회원정보와 플레이리스트를 관리해 보세요.</p>

    </div>


    <!-- 조회 실패 메시지 -->
    <c:if test="${not empty mypageError}">
        <p class="mypage-error">
            <c:out value="${mypageError}"/>
        </p>
    </c:if>


    <!-- 회원정보 조회 성공 -->
    <c:if test="${not empty mypageUser}">

        <section class="mypage-box">

            <span class="mypage-kicker">
                DDANGO MEMBER
            </span>

            <h2>
                <c:out value="${mypageUser.u_nickname}"/>님 당장 노래를 들으셔야 해요
            </h2>

            <!-- 회원정보 -->
            <div class="user-info">

                <div class="info-row">
                    <span>회원번호</span>
                    <strong>${mypageUser.u_id}</strong>
                </div>

                <div class="info-row">
                    <span>아이디</span>
                    <strong>
                        <c:out value="${mypageUser.u_login_id}"/>
                    </strong>
                </div>

                <div class="info-row">
                    <span>닉네임</span>
                    <strong>
                        <c:out value="${mypageUser.u_nickname}"/>
                    </strong>
                </div>

                <div class="info-row">
                    <span>이메일</span>
                    <strong>
                        <c:out value="${mypageUser.u_email}"/>
                    </strong>
                </div>

                <div class="info-row">
                    <span>가입일</span>
                    <strong>
                        <fmt:formatDate value="${mypageUser.u_created_at}"
                                        pattern="yyyy.MM.dd"/>
                    </strong>
                </div>

            </div>


            <!-- 회원정보 수정 버튼 -->
            <div class="mypage-actions">

                <button type="button" onclick="">
                    닉네임 수정
                </button>

                <button type="button" onclick="">
                    비밀번호 변경
                </button>

            </div>


            <!-- 회원 탈퇴 -->
            <div class="withdraw-area">

                <button type="button"
                        class="withdraw-button"
                        onclick="">
                    회원 탈퇴
                </button>

            </div>

        </section>


        <!-- 내 플레이리스트 -->
        <section class="my-playlist">

            <span class="mypage-kicker">
                MY PLAYLIST
            </span>

            <h2>내가 담은 아랑스러운 플리</h2>

            <p>내가 만든 플리다 보아라</p>

            <button type="button" onclick="">
                내 플레이리스트 보기 ↗
            </button>

        </section>

    </c:if>

</main>


<footer>
    <strong>DDANGO 딴거.</strong>
    <span>NOT THAT ONE. THIS ONE. © DDANGO</span>
</footer>

</body>
</html>