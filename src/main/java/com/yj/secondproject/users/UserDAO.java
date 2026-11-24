
package com.yj.secondproject.users;

import com.yj.secondproject.DdangoDB;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;

public class UserDAO {

    // 1. 회원가입
    public static boolean insertUser(UserDTO user) {

        try (SqlSession ss = DdangoDB.connect()) {

            if (ss.insert("ddango.insertUser", user) == 1) {
                ss.commit();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }



}
