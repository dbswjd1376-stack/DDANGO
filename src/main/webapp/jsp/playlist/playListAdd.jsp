<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section class="music-section playlist-page">
   <div class="add-playlist">
       <h1>나만의 플레이 리스트 만들기</h1>
       <br>
       <form action="/plAdd-c" method="POST" enctype="multipart/form-data">
        플레이 리스트 대표 사진  <input type="file" name="pl_img" accept="image/*"> <br>
         플레이 리스트 제목  <input placeholder="제목을 입력해주세요" name="pl_name" required> <br>
         플레이 리스트 설명  <textarea placeholder="플레이 리스트 설명을 입력해주세요" name="pl_desc" required></textarea> <br>

           <button> 플레이 리스트 만들기 </button> <br>
       </form>

   </div>

</section>
