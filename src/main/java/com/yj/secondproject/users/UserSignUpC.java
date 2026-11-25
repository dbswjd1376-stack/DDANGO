
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

        request.getRequestDispatcher("/jsp/main/signup.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        UserDAO.addUser(request);

        String result = (String) request.getAttribute("signupResult");

        if ("success".equals(result)) {
            // 회원가입 성공 시 메인페이지 이동
            response.sendRedirect(request.getContextPath() + "/main?signup=success");

        } else {
            // 회원가입 실패 시 회원가입 페이지 유지
            request.getRequestDispatcher("/jsp/main/signup.jsp").forward(request, response);

        }
    }
}
