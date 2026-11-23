package com.yj.secondproject.users;

import com.yj.secondproject.DdangoDB;
import com.yj.secondproject.TestDTO;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserDAO {
    public static void selAllTest(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();
            List<TestDTO> tests = ss.selectList("sbqqq.getAllTest");
            request.setAttribute("tests", tests);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ss != null) ss.close();
        }
    }
}
