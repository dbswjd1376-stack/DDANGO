package com.yj.secondproject;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DdangoDB {

    private static final SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("MyBatis 설정(config.xml) 로딩 실패", e);
        }
    }

    public static SqlSession connect() {
        return sqlSessionFactory.openSession();
    }
}
