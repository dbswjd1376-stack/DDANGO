package com.yj.secondproject.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class UserLoginC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/jsp/main/login.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        UserDAO.loginUser(request);

        String result = (String) request.getAttribute("loginResult");

        if ("success".equals(result)) {
            response.sendRedirect(request.getContextPath() + "/jsp/main/index.jsp");

        } else {
            request.getRequestDispatcher("/jsp/main/login.jsp").forward(request, response);
        }

    }
}