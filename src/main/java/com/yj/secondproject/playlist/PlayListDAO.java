package com.yj.secondproject.playlist;

import com.yj.secondproject.DdangoDB;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PlayListDAO {
    public static void selAllPlayList(HttpServletRequest request) {

        // 1. db연결

        SqlSession sqlSession = null;

        try {
            sqlSession = DdangoDB.connect();
            List<PlayListDTO> playLists = sqlSession.selectList("ddango.selectAllPlayList");
            request.setAttribute("playLists", playLists);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null) sqlSession.close();
        }


    }
}
