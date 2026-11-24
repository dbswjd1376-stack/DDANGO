
package com.yj.secondproject.users;

import com.yj.secondproject.DdangoDB;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;

public class UserDAO {

    // 1. 회원가입
    public static void addUser(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();

            String loginId = request.getParameter("u_login_id");
            String password = request.getParameter("u_password");
            String nickname = request.getParameter("u_nickname");
            String email = request.getParameter("u_email");

            UserDTO user = new UserDTO(0, loginId, password, nickname, email, null);

            if (ss.insert("ddango.insertUser", user) == 1) {
                System.out.println("회원가입 성공");
                ss.commit();
                request.setAttribute("signupResult", "로그인 성공!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("signupResult", "로그인 실패");

        } finally {
            if (ss != null) {
                ss.close();
            }
        }
    }
}
