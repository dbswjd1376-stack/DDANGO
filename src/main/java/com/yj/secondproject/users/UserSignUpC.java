package com.yj.secondproject.users;

import com.yj.secondproject.TestDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserSignUpC extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        TestDAO.selAllTest(request);

        request.getRequestDispatcher("result.jsp").forward(request,response);



    }

}