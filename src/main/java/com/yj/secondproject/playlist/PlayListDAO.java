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

    public static void addPlayList(HttpServletRequest request) {
        SqlSession sqlSession = null;

        try {
            String savePath = request.getServletContext().getRealPath("/image/playlist");
            File dir = new File(savePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            MultipartRequest mr = new MultipartRequest(
                    request,
                    savePath,
                    1024 * 1024 * 30,
                    "UTF-8",
                    new DefaultFileRenamePolicy()
            );

            String pName = mr.getParameter("pl_name");
            String pDesc = mr.getParameter("pl_desc");
            String typeStr = mr.getParameter("type");
            int mType = "AI".equals(typeStr) ? 2 : 1;

            String fileName = mr.getFilesystemName("pl_img");
            String pImg = (fileName != null)
                    ? "image/playlist/" + fileName
                    : "";

            UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
            int uId = loginUser.getU_id();

            PlayListDTO dto = new PlayListDTO(0, pImg, pName, pDesc, mType, null, uId);

            sqlSession = DdangoDB.connect();
            if (sqlSession.insert("ddango_pl.insertPlayList", dto) == 1) {
                sqlSession.commit();
                System.out.println("플레이리스트 등록 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }
}
