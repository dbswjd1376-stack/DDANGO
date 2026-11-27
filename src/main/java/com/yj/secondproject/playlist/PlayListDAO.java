package com.yj.secondproject.playlist;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yj.secondproject.DdangoDB;
import com.yj.secondproject.users.UserDTO;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class PlayListDAO {

    /**
     * 전체 플레이리스트 목록을 조회해 request의 "playLists" 속성에 저장한다.
     */
    public static void selAllPlayList(HttpServletRequest request) {

        SqlSession sqlSession = null;

        try {
            sqlSession = DdangoDB.connect();
            List<PlayListDTO> playLists = sqlSession.selectList("ddango_pl.selectAllPlayList");
            request.setAttribute("playLists", playLists);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    /**
     * 로그인한 유저(uId)가 작성한 플레이리스트만 조회해 request의 "playLists" 속성에 저장한다.
     */
    public static void selMyPlayList(HttpServletRequest request, int uId) {

        SqlSession sqlSession = null;

        try {
            sqlSession = DdangoDB.connect();
            List<PlayListDTO> playLists = sqlSession.selectList("ddango_pl.myPlayList", uId);
            request.setAttribute("playLists", playLists);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    /**
     * multipart/form-data 요청으로 전달된 이미지와 플레이리스트 정보를 저장하고 DB에 등록한다.
     */
    public static void addPlayList(HttpServletRequest request) {
        SqlSession sqlSession = null;

        try {
            // 배포 경로(target/.../image/playlist)에 폴더가 없으면 생성
            String savePath = request.getServletContext().getRealPath("/image/playlist");
            File dir = new File(savePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // cos 라이브러리로 파일 업로드 처리 (최대 30MB, 중복 파일명은 자동 번호 부여)
            MultipartRequest mr = new MultipartRequest(
                    request,
                    savePath,
                    1024 * 1024 * 30,
                    "UTF-8",
                    new DefaultFileRenamePolicy()
            );

            // multipart 요청은 request.getParameter()가 null이므로 반드시 mr에서 꺼냄
            String pName = mr.getParameter("pl_name");
            String pDesc = mr.getParameter("pl_desc");

            String fileName = mr.getFilesystemName("pl_img");
            String pImg = (fileName != null)
                    ? "image/playlist/" + fileName
                    : "";

            // 세션에 저장된 로그인 유저의 u_id를 FK 값으로 사용
            UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
            int uId = loginUser.getU_id();

            PlayListDTO dto = new PlayListDTO(0, pImg, pName, pDesc, null, uId);

            sqlSession = DdangoDB.connect();
            if (sqlSession.insert("ddango_pl.insertPlayList", dto) == 1) {
                sqlSession.commit(); // MyBatis INSERT는 commit 필수
                System.out.println("플레이리스트 등록 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }
}
