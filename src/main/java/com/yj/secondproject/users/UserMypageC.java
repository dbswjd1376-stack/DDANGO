package com.yj.secondproject.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/mypage")
public class UserMypageC extends HttpServlet {

    // 1. 마이페이지 조회
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        // 비로그인 사용자 접근 제한
        if (session.getAttribute("loginUser") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        // 회원정보 조회
        UserDAO.getUserInfo(request);


        // 회원정보 변경 결과 전달
        request.setAttribute("mypageMessage", session.getAttribute("mypageMessage"));
        request.setAttribute("mypageSuccess", session.getAttribute("mypageSuccess"));


        // 메시지가 반복 출력되지 않도록 삭제
        session.removeAttribute("mypageMessage");
        session.removeAttribute("mypageSuccess");


        // 어디로?
        request.setAttribute("content", "/jsp/main/mypage.jsp");
        request.getRequestDispatcher("/jsp/main/index.jsp").forward(request, response);

    }



    // 2. 회원정보 수정 및 회원 탈퇴
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        // 비로그인 사용자 접근 제한
        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect(request.getContextPath() + "/signup");
            return;
        }

        // 실행할 기능 가져오기
        String action = request.getParameter("action");
        String message;
        boolean success = false;


        // 3. 닉네임 수정

        if ("nickname".equals(action)) {

            UserDAO.updateNickname(request);

            String result = (String) request.getAttribute("nicknameResult");


            if ("success".equals(result)) {
                message = "닉네임이 수정되었습니다.";
                success = true;

            } else if ("invalid".equals(result)) {
                message = "닉네임은 1~50자로 입력해 주세요.";
            } else {
                message = "닉네임 수정에 실패했습니다.";
            }

        }


        // 4. 비밀번호 변경

        else if ("password".equals(action)) {

            UserDAO.updatePassword(request);

            String result = (String) request.getAttribute("passwordResult");

            if ("success".equals(result)) {
                message = "비밀번호가 변경되었습니다.";
                success = true;

            } else if ("empty".equals(result)) {
                message = "비밀번호를 모두 입력해 주세요.";
            } else if ("length".equals(result)) {
                message = "새 비밀번호는 8~255자로 입력해 주세요.";
            } else if ("mismatch".equals(result)) {
                message = "새 비밀번호가 일치하지 않습니다.";
            } else if ("same".equals(result)) {
                message = "기존 비밀번호와 다른 비밀번호를 입력해 주세요.";
            } else if ("wrong".equals(result)) {
                message = "현재 비밀번호가 일치하지 않거나 계정이 존재하지 않습니다.";
            } else {
                message = "비밀번호 변경 중 오류가 발생했습니다.";
            }

        }

        // 5. 회원 탈퇴

        else if ("withdraw".equals(action)) {
            UserDAO.deleteUser(request);

            String result = (String) request.getAttribute("withdrawResult");
            if ("success".equals(result)) {

                // 회원 탈퇴 성공 시 Session 종료
                session.invalidate();

                // 메인페이지로 이동
                response.sendRedirect(request.getContextPath() + "/main");

                return;

            } else if ("disagree".equals(result)) {

                message = "회원 탈퇴에 동의해 주세요.";

            } else if ("empty".equals(result)) {

                message = "현재 비밀번호를 입력해 주세요.";

            } else if ("wrong".equals(result)) {

                message = "현재 비밀번호가 일치하지 않거나 계정이 존재하지 않습니다.";

            } else {

                message = "회원 탈퇴 중 오류가 발생했습니다.";

            }

        }

        // 마이페이지 다시 조회
        response.sendRedirect(request.getContextPath() + "/mypage");

    }

}