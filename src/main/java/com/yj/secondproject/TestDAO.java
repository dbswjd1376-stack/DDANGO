package com.yj.secondproject;

import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TestDAO {
    public static void selAllTest(HttpServletRequest request) {

        SqlSession ss = null;

        try {
            ss = DdangoDB.connect();
            List<TestDTO> tests = ss.selectList("ddango.getAllTest");
            request.setAttribute("tests", tests);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ss != null) ss.close();
        }
    }
}
