<!--도서 제목과 문구 입력-->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>도서 검색</title>
</head>

<body>
<h1>BOOK PLAYLIST</h1>
<p>책 한 권에서 시작되는 나만의 음악</p>

<form action="${pageContext.request.contextPath}/book-search" method="get">
    <h3>도서 제목 </h3>
    <input type="text" name="keyword"
           placeholder="도서 제목을 입력해주세요."
           required>
<%--    입력 필수--%>

    <h3>도서 문구</h3>
    <textarea name="quote"
              placeholder="책에서 인상 깊었던 문구를 입력해주세요."></textarea>
<%--입력 선택--%>
    <button type="submit">찾기</button>

</form>
</body>
</html>
