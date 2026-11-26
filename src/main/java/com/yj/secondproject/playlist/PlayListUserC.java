package com.yj.secondproject.playlist;

import com.yj.secondproject.users.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/plUser-c")
public class PlayListUserC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 유저의 플리를 보여줘야함
        request.setAttribute("content", "/jsp/playlist/playListUser.jsp");
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 플레이 리스트를 추가해야함 (multipart 파일 업로드는 POST)


        response.sendRedirect(request.getContextPath() + "/pl-c");
    }
}
