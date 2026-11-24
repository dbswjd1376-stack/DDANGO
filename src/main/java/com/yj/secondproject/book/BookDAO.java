package com.yj.secondproject.book;

import com.yj.secondproject.DdangoDB;
import com.yj.secondproject.TestDTO;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BookDAO {
    public static void searchBookByTitle(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();
            List<BookDTO> books = ss.selectList("ddango.searchBookByTitle");
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
            List<BookDTO> books = ss.selectList("ddango.getChapterByBookId");
            request.setAttribute("books", books);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ss != null) ss.close();
        }
    }


}




