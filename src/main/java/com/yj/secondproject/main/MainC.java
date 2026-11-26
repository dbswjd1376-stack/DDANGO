package com.yj.secondproject.main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainC extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("content", "/jsp/main/home.jsp");
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 플리 추가 페이지로 보내주기

        request.setAttribute("content", "/jsp/playlist/playListAdd.jsp");
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);
    }
}
