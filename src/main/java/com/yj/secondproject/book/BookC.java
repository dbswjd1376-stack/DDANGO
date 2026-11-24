package com.yj.secondproject.book;
import javax.servlet.http.HttpServletRequest;

public class BookC {

    // 책 제목 검색
    public static void getBookByTitle(HttpServletRequest request) {
        BookDAO.searchBookByTitle(request);
    }

    // 책의 챕터 목록 조회
    public static void getChaptersByBookId(HttpServletRequest request) {
        BookDAO.getChaptersByBookId(request);
    }
}
