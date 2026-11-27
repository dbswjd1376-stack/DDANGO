package com.yj.secondproject.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class UserSignUpC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("content", "/jsp/main/signup.jsp");

        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);

    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        // 회원가입 실행
        UserDAO.addUser(request);

        String result = (String) request.getAttribute("signupResult");

        System.out.println("회원가입 결과 : " + result);


        // 회원가입 성공
        if ("success".equals(result)) {

            // 입력한 아이디와 비밀번호로 자동 로그인
            UserDAO.loginUser(request);


            // Session에 로그인 정보가 저장되었는지 확인
            if (request.getSession().getAttribute("loginUser") != null) {

                System.out.println("자동 로그인 성공");

                response.sendRedirect(request.getContextPath() + "/main");

                return;

            }

            // 자동 로그인 실패
            response.sendRedirect(request.getContextPath() + "/login");

            return;

        }


        // 회원가입 실패
        request.setAttribute("content", "/jsp/main/signup.jsp");
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);

    }

}