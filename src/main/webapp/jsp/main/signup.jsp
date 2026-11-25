
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, initial-scale=1.0">

  <title>회원가입 | DDANGO</title>

  <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700;800;900&family=Noto+Sans+KR:wght@400;500;600;700;800;900&display=swap"
        rel="stylesheet">

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/index.css">

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/signup.css">

</head>

<body>

<!-- 헤더 -->
<header>
  <div class="header-inner">

    <a href="${pageContext.request.contextPath}/jsp/main/index.jsp" class="logo">
      DDANGO<span>딴거</span>
    </a>

    <nav>
      <a href="${pageContext.request.contextPath}/jsp/main/index.jsp">DISCOVER</a>
      <a href="#" onclick="">
        감정별 노래추천
      </a>
      <a href="#" onclick="">
        도서로 노래추천
      </a>
    </nav>

    <a href="${pageContext.request.contextPath}/login" class="login">로그인 ↗</a>

  </div>
</header>

<!-- 회원가입 영역 -->
<main class="signup-main">

  <section class="signup-box">

        <span class="signup-kicker">
            JOIN THE DDANGO CLUB ★
        </span>

    <h1>
      새로운 취향의<br>
      시작, <em>딴거.</em>
    </h1>

    <p class="signup-description">
      나만의 플레이리스트를 만들어 보세요!
    </p>

    <!-- 회원가입 실패 메시지 -->
    <c:if test="${signupResult == 'empty'}">
      <p class="signup-error">
        모든 항목을 입력해 주세요.
      </p>
    </c:if>

    <c:if test="${signupResult == 'length'}">
      <p class="signup-error">
        입력값의 길이를 확인해 주세요.
      </p>
    </c:if>

    <c:if test="${signupResult == 'duplicate'}">
      <p class="signup-error">
        이미 사용 중인 아이디입니다.
      </p>
    </c:if>

    <c:if test="${signupResult == 'passwordMismatch'}">
      <p class="signup-error">
        비밀번호가 일치하지 않습니다.
      </p>
    </c:if>

    <c:if test="${signupResult == 'fail'}">
      <p class="signup-error">
        회원가입에 실패했습니다. 다시 시도해 주세요.
      </p>
    </c:if>

    <!-- 회원가입 입력 폼 -->
    <form action="${pageContext.request.contextPath}/signup" method="post"
          class="signup-form">

      <div class="signup-field">
        <label for="loginId">아이디</label>

        <input type="text"
               id="loginId"
               name="u_login_id"
               placeholder="아이디를 입력하세요"
               maxlength="30"
               required>
      </div>

      <div class="signup-field">
        <label for="password">비밀번호</label>

        <input type="password"
               id="password"
               name="u_password"
               placeholder="8자 이상 입력하세요"
               minlength="8"
               maxlength="128"
               required>
      </div>

      <div class="signup-field">
        <label for="passwordCheck">비밀번호 확인</label>

        <input type="password"
               id="passwordCheck"
               name="password_check"
               placeholder="비밀번호를 다시 입력하세요"
               minlength="8"
               maxlength="128"
               required>
      </div>

      <div class="signup-field">
        <label for="nickname">닉네임</label>

        <input type="text"
               id="nickname"
               name="u_nickname"
               placeholder="닉네임을 입력하세요"
               maxlength="50"
               required>
      </div>

      <div class="signup-field">
        <label for="email">이메일</label>

        <input type="email"
               id="email"
               name="u_email"
               placeholder="example@email.com"
               maxlength="100"
               required>
      </div>

      <button type="submit" class="signup-button">
        회원가입 ↗
      </button>

    </form>

    <p class="signup-bottom">
      이미 계정이 있으신가요?
      <a href="${pageContext.request.contextPath}/login">로그인</a>
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
