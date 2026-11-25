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

            // 입력한 도서 제목이랑 문구 가져오기
            String keyword = request.getParameter("keyword");
            String quote = request.getParameter("quote");

            // 도서 제목으로 db 검색
            List<BookDTO> books = ss.selectList("ddango.searchBookByTitle",
                    keyword);

            // 검색 결과와 문구를 request에 저장
            request.setAttribute("books", books);
            request.setAttribute("quote", quote);

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




