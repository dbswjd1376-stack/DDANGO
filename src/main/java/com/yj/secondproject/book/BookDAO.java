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

            System.out.println("DB 접속 확인 : " +
                    ss.getConnection().getMetaData().getURL());

            System.out.println("DB 계정 확인 : " +
                    ss.getConnection().getMetaData().getUserName());




            // 입력한 도서 제목이랑 문구 가져오기
            String keyword = request.getParameter("keyword");
            String quote = request.getParameter("quote");
            String mode = request.getParameter("mode");


            // 도서 제목으로 db 검색하고 결과를 list<>로 받음
            List<BookDTO> books = ss.selectList("ddango.searchBookByTitle",
                    keyword);


//            int chapterCount = ss.selectOne("ddango.debugChapterCount", 13);
//
//            System.out.println("변신 챕터 개수 : " + chapterCount);
//
//
//            System.out.println("검색된 책 개수 : " + books.size());
//
//            for (BookDTO book : books) {
//                System.out.println("책 ID : " + book.getB_id());
//                System.out.println("책 제목 : " + book.getB_title());
//                System.out.println("챕터 여부 : " + book.getHasChapter());
//            }


            // 검색 결과와 문구를 books라는 이름으로 request에 저장
            // 그러면 jsp에서 ${books}로 꺼내쓰기 가능
            request.setAttribute("books", books);
            request.setAttribute("quote", quote);
            request.setAttribute("mode", mode);

            // 검색된 책이 있는 경우
            if (!books.isEmpty()) {

                BookDTO book = books.get(0);

                // 챕터별 추천을 선택했지만 챕터가 없는 경우
                if ("chapter".equals(mode) && book.getHasChapter() == 0) {
                    request.setAttribute("message",
                            "이 책은 챕터 정보가 없어 전체 플레이리스트만 추천할 수 있습니다.");
                }
            }

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




