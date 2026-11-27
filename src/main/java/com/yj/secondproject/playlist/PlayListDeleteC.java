package com.yj.secondproject.playlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/plDel-c")
public class PlayListDeleteC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 이제 플리 삭제할거임
        PlayListDAO.delPlayList(request);
        // 어디로?
        response.sendRedirect("/plUser-c");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        
    }
}
