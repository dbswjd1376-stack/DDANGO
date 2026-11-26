package com.yj.secondproject.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class UserLoginC extends HttpServlet {

    // 로그인 페이지 이동
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("content", "/jsp/main/login.jsp");

        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);

    }


    // 로그인 처리
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        UserDAO.loginUser(request);

        String result = (String) request.getAttribute("loginResult");

        // 로그인 성공
        if ("success".equals(result)) {
            response.sendRedirect(request.getContextPath() + "/main");

        } else {
            // 로그인 실패
            request.setAttribute("content", "/jsp/main/login.jsp");

            request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);
        }

    }
}