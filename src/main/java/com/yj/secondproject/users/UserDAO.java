
package com.yj.secondproject.users;

import com.yj.secondproject.DdangoDB;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;

public class UserDAO {

    // 회원가입
    public static boolean addUser(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();

            String loginId = request.getParameter("u_login_id");
            String password = request.getParameter("u_password");
            String nickname = request.getParameter("u_nickname");
            String email = request.getParameter("u_email");

            if (loginId == null || loginId.trim().isEmpty()
                    || password == null || password.isEmpty()
                    || nickname == null || nickname.trim().isEmpty()
                    || email == null || email.trim().isEmpty()) {

                request.setAttribute("signupError", "빈칸 없이 입력해주세요!!!");
                return false;
            }

            int count = ss.selectOne("ddango.checkLoginId", loginId);

            if (count > 0) {
                request.setAttribute("signupError", "이미 사용 중인 아이디입니다ㅜㅜ");
                return false;
            }

            UserDTO user = new UserDTO();

            user.setULoginId(loginId);
            user.setUPassword(password);
            user.setUNickname(nickname);
            user.setUEmail(email);

            if (ss.insert("ddango.insertUser", user) == 1) {

                ss.commit();
                System.out.println("회원가입 성공!");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("signupError", "회원가입 오류발생!!!!!");

        } finally {
            if (ss != null) {
                ss.close();
            }
        }

        return false;
    }
}
