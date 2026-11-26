
package com.yj.secondproject.book;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/book-search")
public class BookC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // 검색 하는 일
        BookDAO.searchBookByTitle(request);
        // 어디로?
        request.getRequestDispatcher("jsp/book/bookSearchResult.jsp").forward(request, response);
    }
}
