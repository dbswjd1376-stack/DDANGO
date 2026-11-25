
package com.yj.secondproject.users;

import com.yj.secondproject.DdangoDB;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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

    // 2. 로그인
    public static void loginUser(HttpServletRequest request) {

        SqlSession ss = null;

        try {

            ss = DdangoDB.connect();

            String loginId = request.getParameter("u_login_id");
            String password = request.getParameter("u_password");

            HashMap<String, String> vals = new HashMap<>();

            vals.put("loginId", loginId);
            vals.put("password", password);

            UserDTO user = ss.selectOne("ddango.loginUser", vals);

            if (user != null) {

                request.getSession().setAttribute("loginUser", user);

                request.setAttribute("loginResult", "success");

                System.out.println("로그인 성공");

            } else {

                request.setAttribute("loginResult", "fail");
                System.out.println("로그인 실패");

            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("loginResult", "fail");

        } finally {
            if (ss != null) {
                ss.close();
            }
        }
    }

    // 3. 마이페이지 회원정보 조회
    public static void getUserInfo (HttpServletRequest request) {

        SqlSession ss = null;

        try {

            ss = DdangoDB.connect();

         UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");

         int u_id = loginUser.getU_id();

         UserDTO user = ss.selectOne("ddango.getUserInfo", u_id);

         if (user != null) {

             request.setAttribute("mypageUser", user);
         }  else {
             request.setAttribute("mypageError", "회원정보를 찾을 수 없습니다");
         }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mypageError", "회원정보 조회에 실패했습니다");

        } finally {
            if (ss != null) {
                ss.close();
            }
        }
    }






}
