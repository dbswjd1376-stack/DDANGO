<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class ="contents">
    <jsp:include page="${}"></jsp:include>
</div>
<c:forEach items="${playLists}" var="p">
    <div class="card">
        <div class="card-img">
            <img src="${p.p_img}" >
        </div>
        <div class="card-body">
            <div>${p.p_id}</div>  <hr>
            <div class="card-name">${p.p_name}</div>
            <div class="card-price">${p.p_desc}</div>
            <div class="card-exp">${p.p_date}</div>
        </div>
    </div>
</c:forEach>
</body>
</html>
