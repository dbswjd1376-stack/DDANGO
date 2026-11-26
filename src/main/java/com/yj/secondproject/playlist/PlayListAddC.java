package com.yj.secondproject.playlist;

import com.yj.secondproject.users.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/plAdd-c")
public class PlayListAddC extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 플리 추가 페이지로 보내주기
        request.setAttribute("content", "/jsp/playlist/playListAdd.jsp");
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 플레이 리스트를 추가해야함 (multipart 파일 업로드는 POST)
        PlayListDAO.addPlayList(request);

        response.sendRedirect(request.getContextPath() + "/plUser-c");
    }
}
