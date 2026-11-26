package com.yj.secondproject.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/mypage")
public class UserMypageC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // 1. 비로그인 사용자 접근 제한
        if (request.getSession().getAttribute("loginUser") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        // 2. 로그인한 회원정보 조회
        UserDAO.getUserInfo(request);

        // 3. 불러올 본문 JSP 설정
        request.setAttribute("content", "/jsp/main/mypage.jsp");

        // 4. 공통 레이아웃으로 이동
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        



    }



}