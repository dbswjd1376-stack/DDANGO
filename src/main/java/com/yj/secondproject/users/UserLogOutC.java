package com.yj.secondproject.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class UserLogOutC extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().invalidate();
        System.out.println("로그아웃 성공");

        response.sendRedirect("index.jsp");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        
    }
}