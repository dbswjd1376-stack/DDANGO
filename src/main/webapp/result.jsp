<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<c:forEach items="${tests}" var="t">
    <div class="card">
        <div class="card-img">
                    </div>
        <div class="card-body">
            <div>${t.t_no}</div>  <hr>
            <div class="card-name">${t.t_name}</div>
            <div class="card-price">${t.t_age}원</div>

        </div>
    </div>
</c:forEach>

</body>
</html>