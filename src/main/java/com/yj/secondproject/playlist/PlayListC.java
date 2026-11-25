
package com.yj.secondproject.playlist;

import com.yj.secondproject.book.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pl-c")
public class PlayListC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 전체 플레이 리스트를 보여줘야함
            PlayListDAO.selAllPlayList(request);
        // 어디로?
        request.getRequestDispatcher("/jsp/playlist/playListMain.jsp").forward(request, response);
    }
}
