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

<%-- 노래 감정 tag data-mood는 세밀 감정 tag --%>
<section id="music" data-api="${pageContext.request.contextPath}/api/music">
    <button type="button" data-mood="relaxed" data-tag="chill">편안해요</button>
    <button type="button" data-mood="good-mood" data-tag="happy">기분이 좋아요</button>
    <button type="button" data-mood="feeling-down" data-tag="sad">우울해요</button>
    <button type="button" data-mood="satisfaction" data-tag="happy">만족</button>
    <button type="button" data-mood="peacefulness" data-tag="calm">평온</button>
    <button type="button" data-mood="joy" data-tag="upbeat">즐거움</button>
    <button type="button" data-mood="excitement" data-tag="romantic">설렘</button>
    <button type="button" data-mood="cheerfulness" data-tag="fun">유쾌함</button>
    <button type="button" data-mood="happiness" data-tag="happy">행복</button>
    <button type="button" data-mood="gratitude" data-tag="happy">감사</button>
    <button type="button" data-mood="nostalgia" data-tag="nostalgia">추억</button>
    <button type="button" data-mood="disappointment" data-tag="melancholy">아쉬움</button>
    <button type="button" data-mood="hurt-feelings" data-tag="sad">서운함</button>
    <button type="button" data-mood="emptiness" data-tag="melancholy">허전함</button>
    <button type="button" data-mood="forlornness" data-tag="melancholy">쓸쓸함</button>
    <button type="button" data-mood="sorrow" data-tag="sad">비애</button>
    <button type="button" data-mood="tears" data-tag="sad">눈물</button>
    <button type="button" data-mood="loneliness" data-tag="loneliness">외로움</button>
    <button type="button" data-mood="discouragement" data-tag="sad">낙담</button>
    <button type="button" data-mood="despair" data-tag="dark">절망</button>
    <button type="button" data-mood="indignation" data-tag="angry">원통함</button>
    <button type="button" data-mood="helplessness" data-tag="melancholy">무력함</button>
    <button type="button" data-mood="longing" data-tag="nostalgia">그리움</button>
    <button type="button" data-mood="lingering-attachment" data-tag="melancholy">미련</button>
    <button type="button" data-mood="regret" data-tag="melancholy">후회</button>
    <button type="button" data-mood="sense-of-loss" data-tag="sad">상실감</button>
    <button type="button" data-mood="resignation" data-tag="melancholy">체념</button>
    <button type="button" data-mood="annoyance" data-tag="angry">짜증</button>
    <button type="button" data-mood="displeasure" data-tag="angry">불쾌</button>
    <button type="button" data-mood="anger" data-tag="angry">화남</button>
    <button type="button" data-mood="disapproval" data-tag="angry">못마땅함</button>
    <button type="button" data-mood="disgust" data-tag="aggressive">혐오감</button>
    <button type="button" data-mood="mockery" data-tag="aggressive">조롱</button>
    <button type="button" data-mood="sense-of-superiority" data-tag="energetic">우월감</button>
    <button type="button" data-mood="jealousy" data-tag="angry">질투</button>
    <button type="button" data-mood="resentment" data-tag="angry">원망</button>
    <button type="button" data-mood="worry" data-tag="anxiety">걱정</button>
    <button type="button" data-mood="restlessness" data-tag="anxiety">초조</button>
    <button type="button" data-mood="concern" data-tag="anxiety">우려</button>
    <button type="button" data-mood="hesitation" data-tag="anxiety">망설임</button>
    <button type="button" data-mood="tension" data-tag="nervous">긴장</button>
    <button type="button" data-mood="anxiety" data-tag="anxiety">불안</button>
    <button type="button" data-mood="fear" data-tag="terror">무서움</button>
    <button type="button" data-mood="feeling-threatened" data-tag="dark">위협감</button>
    <button type="button" data-mood="shivers" data-tag="eerie">전율</button>
    <button type="button" data-mood="panic" data-tag="anxiety">패닉</button>
    <button type="button" data-mood="shock" data-tag="horror">경악</button>
    <button type="button" data-mood="admiration" data-tag="romantic">동경</button>
    <button type="button" data-mood="shyness" data-tag="romantic">수줍음</button>
    <button type="button" data-mood="affection" data-tag="love">애정</button>
    <button type="button" data-mood="tenderness" data-tag="romantic">애틋함</button>
    <button type="button" data-mood="unrequited-love" data-tag="sad">짝사랑</button>
    <button type="button" data-mood="love" data-tag="love">사랑</button>
    <%-- api 들어갔는지 확인 하는 용 링크 --%>
    <a href="${pageContext.request.contextPath}/api/music/recommend?tag=romantic">Last.fm 연결 테스트</a>
    <%--  세부 감정 분류 확인 하는 링크  --%>
    <a href="http://localhost:8080/api/music/recommend?mood=satisfaction">test</a>
    <%--  track 목록  --%>
    <p id="music-status" role="status"></p>
    <ul id="music-tracks"></ul>
    <%--  나오는 영상 화면 크기 및 화면 숨기는 기능  --%>
    <iframe id="music-player" title="Youtube 음악 플레이어"
    width="480" height="270" allowfullscreen
    referrerpolicy="strict-origin-when-cross-origin" hidden></iframe>
</section>

</body>

</html>
