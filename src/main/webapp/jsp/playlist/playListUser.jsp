<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section class="music-section playlist-page">
    <div class="section-top">
        <div>
            <span class="section-number">MY PLAYLIST</span>
            <h2>내가 만든 딴거</h2>
            <p>나만의 취향이 담긴 플레이리스트를 구경해 보세요.</p>
        </div>
        <button type="button" class="more" onclick=location.href="/plAdd-c">플리 만들기 +</button>
    </div>

    <c:choose>
        <c:when test="${empty playLists}">
            <div class="playlist-empty">
                아직 등록된 플레이리스트가 없습니다.
            </div>
        </c:when>
        <c:otherwise>
            <div class="playlist-grid">
                <c:forEach items="${playLists}" var="p">
                    <article class="playlist" data-id="${p.p_id}" data-title="${p.p_name}">
                        <div class="cover cover-peach has-image">
                            <c:if test="${not empty p.p_img}">
                                <img class="cover-img" src="${p.p_img}" alt="${p.p_name}">
                            </c:if>

                            <span class="tag">PLAYLIST</span>

                            <span class="cover-bottom">NO. ${p.p_id}</span>
                            <button type="button" class="play-circle" aria-label="플리 보기">▶</button>
                        </div>

                        <h3><c:out value="${p.p_name}" /></h3>
                        <p class="playlist-desc"><c:out value="${p.p_desc}" /></p>
                        <p class="playlist-meta">
                            @<c:out value="${p.u_id}" /> ·
                            <fmt:formatDate value="${p.p_date}" pattern="yyyy.MM.dd" />
                            <div>
                        <form action="">
                            <button>수정하기</button>
                        </form>
                        <form action="plDel-c">
                            <button>삭제하기</button>
                        </form>

                    </div>
                        </p>
                    </article>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</section>
