
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/book-search" method="get">
    <input type="text" name="title" placeholder="책 제목 입력">
    <button type="submit">찾기</button>
</form>
</body>
</html>
