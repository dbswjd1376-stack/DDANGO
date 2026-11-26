
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


    // 4. 닉네임 수정
    public static void updateNickname(HttpServletRequest request) {

        SqlSession ss = null;

        try {

            ss = DdangoDB.connect();

            // Session에 저장된 로그인 회원정보 가져오기
            UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");

            // 회원번호 가져오기
            int u_id = loginUser.getU_id();

            // JSP에서 입력한 새로운 닉네임 가져오기
            String nickname = request.getParameter("nickname");

            // 닉네임 입력값 검사
            if (nickname == null || nickname.trim().isEmpty() || nickname.trim().length() > 50) {
                request.setAttribute("nicknameResult", "invalid");
                return;

            }
            nickname = nickname.trim();

            // HashMap에 데이터 담기
            HashMap<String, Object> vals = new HashMap<>();

            vals.put("u_id", u_id);
            vals.put("nickname", nickname);

            // 닉네임 수정
            if (ss.update("ddango.updateNickname", vals) == 1) {

                System.out.println("닉네임 수정 성공");

                ss.commit();

                // Session에 저장된 닉네임도 수정
                loginUser.setU_nickname(nickname);

                request.getSession().setAttribute("loginUser", loginUser);

                request.setAttribute("nicknameResult", "success");

            } else {

                System.out.println("닉네임 수정 실패");

                request.setAttribute("nicknameResult", "fail");

            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("nicknameResult", "fail");

        } finally {

            if (ss != null) {
                ss.close();
            }

        }

    }


    // 5. 비밀번호 변경
    public static void updatePassword(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();
            UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");
            int u_id = loginUser.getU_id();
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            if (currentPassword == null || currentPassword.isEmpty()
                    || newPassword == null || newPassword.isEmpty()
                    || confirmPassword == null || confirmPassword.isEmpty()) {

                request.setAttribute("passwordResult", "empty");

                return;
            }

            if (newPassword.length() < 8 || newPassword.length() > 255) {
                request.setAttribute("passwordResult", "length");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("passwordResult", "mismatch");
                return;
            }

            if (currentPassword.equals(newPassword)) {
                request.setAttribute("passwordResult", "same");
                return;
            }

            HashMap<String, Object> vals = new HashMap<>();
            vals.put("u_id", u_id);
            vals.put("currentPassword", currentPassword);
            vals.put("newPassword", newPassword);

            if (ss.update("ddango.updatePassword", vals) == 1) {
                ss.commit();
                System.out.println("비밀번호 변경 성공");
                request.setAttribute("passwordResult", "success");

            } else {

                System.out.println("비밀번호 변경 실패");
                request.setAttribute("passwordResult", "wrong");

            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("passwordResult", "fail");

        } finally {
            if (ss != null) {
                ss.close();
            }
        }

    }

    // 6. 회원 탈퇴
    public static void deleteUser(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();
            UserDTO loginUser = (UserDTO) request.getSession().getAttribute("loginUser");

            int u_id = loginUser.getU_id();

            String currentPassword = request.getParameter("currentPassword");
            String withdrawConfirm = request.getParameter("withdrawConfirm");


            // 회원 탈퇴 동의 여부 확인
            if (!"yes".equals(withdrawConfirm)) {
                request.setAttribute("withdrawResult", "disagree");
                return;
            }

            // 현재 비밀번호 입력 여부 확인
            if (currentPassword == null || currentPassword.isEmpty()) {
                request.setAttribute("withdrawResult", "empty");
                return;
            }

            HashMap<String, Object> vals = new HashMap<>();
            vals.put("u_id", u_id);
            vals.put("currentPassword", currentPassword);

            // 회원 탈퇴
            if (ss.delete("ddango.deleteUser", vals) == 1) {
                ss.commit();
                System.out.println("회원 탈퇴 성공");
                request.setAttribute("withdrawResult", "success");

            } else {
                System.out.println("회원 탈퇴 실패");
                request.setAttribute("withdrawResult", "wrong");

            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("withdrawResult", "fail");

        } finally {
            if (ss != null) {
                ss.close();

            }

        }

    }






}
