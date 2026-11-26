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

        <p>나의 회원정보를 확인해 보세요.</p>

    </div>


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

                <!-- 회원번호 -->
                <div class="info-row">

                    <span>회원번호</span>

                    <strong>
                            ${mypageUser.u_id}
                    </strong>

                </div>


                <!-- 아이디 -->
                <div class="info-row">

                    <span>아이디</span>

                    <strong>
                        <c:out value="${mypageUser.u_login_id}"/>
                    </strong>

                </div>


                <!-- 닉네임 -->
                <div class="info-row">

                    <span>닉네임</span>

                    <strong>
                        <c:out value="${mypageUser.u_nickname}"/>
                    </strong>

                </div>


                <!-- 이메일 -->
                <div class="info-row">

                    <span>이메일</span>

                    <strong>
                        <c:out value="${mypageUser.u_email}"/>
                    </strong>

                </div>


                <!-- 가입일 -->
                <div class="info-row">

                    <span>가입일</span>

                    <strong>
                        <fmt:formatDate
                                value="${mypageUser.u_created_at}"
                                pattern="yyyy.MM.dd"/>
                    </strong>

                </div>

            </div>


            <!-- 회원정보 수정 버튼 -->
            <div class="mypage-actions">

                <button type="button" onclick="comingSoon()">
                    닉네임 수정
                </button>

                <button type="button" onclick="comingSoon()">
                    비밀번호 변경
                </button>

            </div>


            <!-- 회원 탈퇴 -->
            <div class="withdraw-area">

                <button type="button"
                        class="withdraw-button"
                        onclick="comingSoon()">
                    회원 탈퇴
                </button>

            </div>

        </section>


        <!-- 내 플레이리스트 -->
        <section class="my-playlist">

            <span class="mypage-kicker">
                MY PLAYLIST
            </span>

            <h2>내가 담은 취향들.</h2>

            <p>나만의 플레이리스트를 만나보세요.</p>

            <button type="button" onclick="comingSoon()">
                내 플레이리스트 보기 ↗
            </button>

        </section>

    </c:if>

</div>