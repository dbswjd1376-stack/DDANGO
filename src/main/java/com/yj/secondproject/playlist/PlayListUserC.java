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

    /**
     * 로그인한 유저의 플레이리스트 목록 화면(playListUser.jsp)으로 이동한다.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 세션에서 로그인 유저 확인 (비로그인 시 로그인 페이지로 이동)
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 로그인 유저의 u_id로 본인 플레이리스트만 조회 후 메인 틀(index.jsp)에 include
        PlayListDAO.selMyPlayList(request, loginUser.getU_id());
        request.setAttribute("content", "/jsp/playlist/playListUser.jsp");
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/pl-c");
    }
}
