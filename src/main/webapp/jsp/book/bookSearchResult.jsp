<!--최종 추천 결과 출력-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>도서 검색 결과</title>
</head>
<body>

<h1>도서 검색 결과</h1>

<c:choose>
    <c:when test="${not empty books}">

        <c:forEach var="book" items="${books}">

            <h2>${book.b_title}</h2>
            <p>저자 : ${book.b_author}</p>
            <p>장르 : ${book.b_genre}</p>
            <p>도서 설명 : ${book.b_description}</p>

            <p>입력한 문구 : ${quote}</p>

            <hr>

        </c:forEach>

    </c:when>

    <c:otherwise>
        <p>DB에서 해당 도서를 찾을 수 없습니다.</p>
        <p>AI 분석을 진행할 수 있습니다.</p>
    </c:otherwise>
</c:choose>

</body>
</html>

