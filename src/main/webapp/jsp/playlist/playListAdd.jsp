<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section class="music-section playlist-page">
   <div class="add-playlist">
       <h1>나만의 플레이 리스트 만들기</h1>
       <br>
       <form action="/plAdd-c">
        플레이 리스트 대표 사진  <input type="file"> <br>
         플레이 리스트 제목  <input placeholder="제목을 입력해주세요" required> <br>
         플레이 리스트 설명  <textarea placeholder="플레이 리스트 설명을 입력해주세요" required></textarea> <br>
           플레이 리스트 타입 <input type="radio" name="type" value="normal"> 일반 곡
           <input type="radio" name="type" value="AI">AI곡 <br>

           <button> 플레이 리스트 만들기 </button> <br>
       </form>




   </div>

</section>
