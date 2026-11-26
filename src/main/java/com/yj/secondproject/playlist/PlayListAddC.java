package com.yj.secondproject.playlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/plAdd-c")
public class PlayListAddC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 플레이 리스트를 추가해야함
        PlayListDAO.addPlayList(request);

        // 어디로?
        request.setAttribute("content", "/jsp/playlist/playListUser.jsp");
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        
    }
}
