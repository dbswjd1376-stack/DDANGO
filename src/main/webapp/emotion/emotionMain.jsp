<%--
  Created by IntelliJ IDEA.
  User: thrma
  Date: 2026-11-24
  Time: 오전 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script defer src="${pageContext.request.contextPath}/js/emotionJS/ApiJs.js"></script>
</head>
<body>

<section id="music" data-api="${pageContext.request.contextPath}/api/music">
    <button type="button" data-tag="chill">편안해요</button>
    <button type="button" data-tag="happy">기분이 좋아요</button>
    <button type="button" data-tag="sad">우울해요</button>
    <a href="${pageContext.request.contextPath}/api/music/recommend?tag=chill">Last.fm 연결 테스트</a>
    <p id="music-status" role="status"></p>
    <ul id="music-tracks"></ul>

    <iframe id="music-player" title="Youtube 음악 플레이어"
    width="480" height="270" allowfullscreen
    referrerpolicy="strict-origin-when-cross-origin" hidden></iframe>
</section>

</body>

</html>
