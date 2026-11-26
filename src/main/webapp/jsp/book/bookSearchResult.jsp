
<!-- 최종 추천 결과 출력 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--html, head, body 태그 제거: AJAX로 결과 부분만 가져오기--%>


<c:choose>
    <c:when test="${not empty books}">
<%--        DB에서 검색한 책 목록이 비어 있지 않다면--%>

        <c:forEach var="book" items="${books}">

            <h2>${book.b_title}</h2>
            <p>저자 : ${book.b_author}</p>
            <p>장르 : ${book.b_genre}</p>
            <p>도서 설명 : ${book.b_description}</p>
            <p>입력한 문구 : ${quote}</p>


            <%-- 챕터별 플리를 선택한 경우 --%>
            <c:choose>

                <%-- 챕터가 있는 책 --%>
                <c:when test="${mode == 'chapter' && book.hasChapter == 1}">
                    <h3>챕터마다 달라지는 분위기에 맞춘 플레이리스트</h3>
                </c:when>

                <%-- 챕터가 없는 책 --%>
                <c:when test="${mode == 'chapter' && book.hasChapter == 0}">
                    <p>이 책은 챕터 정보가 없어 전체 플레이리스트만 추천할 수 있습니다.</p>
                </c:when>

                <%-- 전체 플리를 선택한 경우 --%>
                <c:otherwise>
                    <h3>이러한 플리는 어떠신가요?</h3>
                </c:otherwise>

            </c:choose>
        </c:forEach>

    </c:when>

<%--    db에 책이 없는 경우--%>
    <c:otherwise>
        <p>DB에서 해당 도서를 찾을 수 없습니다.</p>
        <p>AI 분석을 진행할 수 있습니다.</p>
    </c:otherwise>
</c:choose>


</body>
</html>
