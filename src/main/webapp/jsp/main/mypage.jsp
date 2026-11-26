<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="mypage-main">

    <!-- 마이페이지 제목 -->
    <div class="mypage-title">

        <span class="mypage-kicker">
            MY DDANGO ★
        </span>

        <h1>나만의 취향, <em>딴거.</em></h1>

        <p>나의 회원정보와 플레이리스트를 관리해 보세요.</p>

    </div>


    <!-- 회원정보 수정 결과 메시지 -->
    <c:if test="${not empty requestScope.mypageMessage}">

        <p class="mypage-message ${mypageSuccess ? 'message-success' : 'message-error'}">

            <c:out value="${requestScope.mypageMessage}"/>

        </p>

    </c:if>


    <!-- 회원정보 조회 실패 메시지 -->
    <c:if test="${not empty requestScope.mypageError}">

        <p class="mypage-error">
            <c:out value="${requestScope.mypageError}"/>
        </p>

    </c:if>


    <!-- 회원정보 조회 성공 -->
    <c:if test="${not empty sessionScope.loginUser
                  and not empty requestScope.mypageUser}">

        <section class="mypage-box">

            <span class="mypage-kicker">
                DDANGO MEMBER
            </span>

            <h2>
                <c:out value="${mypageUser.u_nickname}"/>님, 반가워요!
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

                        <fmt:formatDate
                                value="${mypageUser.u_created_at}"
                                pattern="yyyy.MM.dd"/>

                    </strong>

                </div>

            </div>


            <!-- 회원정보 관리 -->
            <div class="account-settings">


                <!-- 1. 닉네임 수정 -->
                <details class="account-detail">

                    <summary>닉네임 수정</summary>

                    <form action="${pageContext.request.contextPath}/mypage"
                          method="post"
                          class="account-form">

                        <input type="hidden"
                               name="action"
                               value="nickname">

                        <input type="hidden"
                               name="token"
                               value="${sessionScope.mypageToken}">

                        <div class="account-field">

                            <label for="nickname">
                                새로운 닉네임
                            </label>

                            <input type="text"
                                   id="nickname"
                                   name="nickname"
                                   placeholder="새로운 닉네임을 입력하세요"
                                   maxlength="50"
                                   required>

                        </div>

                        <button type="submit"
                                class="account-submit">

                            수정 완료 ↗

                        </button>

                    </form>

                </details>


                <!-- 2. 비밀번호 변경 -->
                <details class="account-detail">

                    <summary>비밀번호 변경</summary>

                    <form action="${pageContext.request.contextPath}/mypage"
                          method="post"
                          class="account-form">

                        <input type="hidden"
                               name="action"
                               value="password">

                        <input type="hidden"
                               name="token"
                               value="${sessionScope.mypageToken}">


                        <div class="account-field">

                            <label for="currentPassword">
                                현재 비밀번호
                            </label>

                            <input type="password"
                                   id="currentPassword"
                                   name="currentPassword"
                                   placeholder="현재 비밀번호"
                                   autocomplete="current-password"
                                   required>

                        </div>


                        <div class="account-field">

                            <label for="newPassword">
                                새 비밀번호
                            </label>

                            <input type="password"
                                   id="newPassword"
                                   name="newPassword"
                                   placeholder="새 비밀번호 (8자 이상)"
                                   minlength="8"
                                   maxlength="255"
                                   autocomplete="new-password"
                                   required>

                        </div>


                        <div class="account-field">

                            <label for="confirmPassword">
                                새 비밀번호 확인
                            </label>

                            <input type="password"
                                   id="confirmPassword"
                                   name="confirmPassword"
                                   placeholder="새 비밀번호를 다시 입력하세요"
                                   autocomplete="new-password"
                                   required>

                        </div>


                        <button type="submit"
                                class="account-submit">

                            변경 완료 ↗

                        </button>

                    </form>

                </details>


                <!-- 3. 회원 탈퇴 -->
                <details class="account-detail withdraw-detail">

                    <summary>회원 탈퇴</summary>

                    <form action="${pageContext.request.contextPath}/mypage"
                          method="post"
                          class="account-form"
                          onsubmit="return confirm('정말 탈퇴하시겠습니까?')">

                        <input type="hidden"
                               name="action"
                               value="withdraw">

                        <input type="hidden"
                               name="token"
                               value="${sessionScope.mypageToken}">


                        <p class="withdraw-warning">

                            회원 탈퇴 시 계정 정보가 삭제되며
                            복구할 수 없습니다.

                        </p>


                        <div class="account-field">

                            <label for="withdrawPassword">
                                현재 비밀번호
                            </label>

                            <input type="password"
                                   id="withdrawPassword"
                                   name="currentPassword"
                                   placeholder="현재 비밀번호를 입력하세요"
                                   autocomplete="current-password"
                                   required>

                        </div>


                        <label class="withdraw-agree">

                            <input type="checkbox"
                                   name="withdrawConfirm"
                                   value="yes"
                                   required>

                            회원 탈퇴에 동의합니다.

                        </label>


                        <button type="submit"
                                class="withdraw-submit">

                            회원 탈퇴

                        </button>

                    </form>

                </details>

            </div>

        </section>


        <!-- 내 플레이리스트 -->
        <section class="my-playlist">

            <span class="mypage-kicker">
                MY PLAYLIST
            </span>

            <h2>내가 담은 취향들.</h2>

            <p>나만의 플레이리스트를 만나보세요.</p>

            <button type="button"
                    onclick="location.href='${pageContext.request.contextPath}/pl-c'">
                내 플레이리스트 보기 ↗
            </button>

        </section>

    </c:if>

</div>