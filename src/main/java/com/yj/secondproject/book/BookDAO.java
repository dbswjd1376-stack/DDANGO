package com.yj.secondproject.book;

import com.yj.secondproject.DdangoDB;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BookDAO {
    public static void searchBookByTitle(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();
            List<BookDTO> books = ss.selectList("ddango.searchBookByTitle",
                    request.getParameter("keyword")); //검색어도 함께 전달
            request.setAttribute("books", books);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ss != null) ss.close();
        }
    }

    public static void getChaptersByBookId(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();
            int bId = Integer.parseInt(request.getParameter("bId"));
            List<BookChapterDTO> chapters =
                    ss.selectList("ddango.getChaptersByBookId", bId);
           
            request.setAttribute("chapters", chapters);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ss != null) ss.close();
        }
    }


}




