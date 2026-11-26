package com.yj.secondproject.playlist;

import com.yj.secondproject.DdangoDB;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class PlayListDAO {
    public static void selAllPlayList(HttpServletRequest request) {

        // 1. db연결

        SqlSession sqlSession = null;

        try {
            sqlSession = DdangoDB.connect();
            List<PlayListDTO> playLists = sqlSession.selectList("ddango_pl.selectAllPlayList");
            request.setAttribute("playLists", playLists);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null) sqlSession.close();
        }


    }

    public static void addPlayList(HttpServletRequest request) {
//        String p_img;
//         String p_name;
//         String p_desc;
//         int m_type;
//         Date p_date;
//         String u_id;
        SqlSession sqlSession = null;
        

//        request.getParameter()

        try {
            sqlSession = DdangoDB.connect();
            

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null) sqlSession.close();
        }
    }
}
