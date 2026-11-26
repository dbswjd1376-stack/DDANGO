package com.yj.secondproject.emotion;

import com.yj.secondproject.users.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/emotion")
public class EmotionC extends HttpServlet {

    // 로그인 페이지 이동
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("content", "/emotion/emotionMain.jsp");

        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);

    }



}