package com.yj.secondproject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class HelloServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        TestDAO.selAllTest(request);

        request.getRequestDispatcher("result.jsp").forward(request,response);



    }

}