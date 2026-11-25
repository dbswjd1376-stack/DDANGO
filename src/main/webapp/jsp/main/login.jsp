<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>로그인 | DDANGO</title>

  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700;800;900&family=Noto+Sans+KR:wght@400;500;600;700;800;900&display=swap"
        rel="stylesheet">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/src/main/webapp/css/index.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/src/main/webapp/css/signup.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/src/main/webapp/css/login.css">

</head>

<body>

<!-- 헤더 -->
<header>
  <div class="header-inner">

    <a href="index.jsp" class="logo">
      DDANGO<span>딴거</span>
    </a>

    <nav>
      <a href="index.jsp">DISCOVER</a>

      <a href="#" onclick="">
        감정별 노래추천
      </a>

      <a href="#" onclick="">
        도서로 노래추천
      </a>
    </nav>

    <a href="signup" class="login">
      회원가입 ↗
    </a>

  </div>
</header>


<!-- 로그인 영역 -->
<main class="signup-main login-main">

  <section class="signup-box login-box">

        <span class="signup-kicker">
            WELCOME BACK ★
        </span>

    <h1>
      오늘도 새로운<br>
      취향, <em>딴거.</em>
    </h1>

    <p class="signup-description">
      오늘의 딴거를 발견해 보세요.
    </p>


    <!-- 로그인 실패 메시지 -->
    <c:if test="${loginResult == 'fail'}">

      <p class="signup-error">
        아이디 또는 비밀번호가 일치하지 않습니다.
      </p>

    </c:if>


    <!-- 로그인 입력 폼 -->
    <form action="login" method="post" class="signup-form">

      <!-- 아이디 -->
      <div class="signup-field">

        <label for="loginId">아이디</label>

        <input type="text"
               id="loginId"
               name="u_login_id"
               placeholder="아이디를 입력하세요"
               autocomplete="username"
               required>

      </div>


      <!-- 비밀번호 -->
      <div class="signup-field">

        <label for="password">비밀번호</label>

        <input type="password"
               id="password"
               name="u_password"
               placeholder="비밀번호를 입력하세요"
               autocomplete="current-password"
               required>

      </div>


      <!-- 로그인 버튼 -->
      <button type="submit" class="signup-button">
        로그인 ↗
      </button>

    </form>


    <!-- 회원가입 페이지 이동 -->
    <p class="signup-bottom">
      아직 계정이 없으신가요?
      <a href="signup">회원가입</a>
    </p>

  </section>

</main>


<!-- 푸터 -->
<footer>
  <strong>DDANGO 딴거.</strong>
  <span>NOT THAT ONE. THIS ONE. © DDANGO</span>
</footer>

</body>
</html>