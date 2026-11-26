<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 로그인 영역 -->
<div class="signup-main login-main">

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
    <form action="${pageContext.request.contextPath}/login"
          method="post" class="signup-form">

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
      <a href="${pageContext.request.contextPath}/signup">
        회원가입
      </a>
    </p>

  </section>

</div>