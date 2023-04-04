package org.example.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static final SqlSessionFactory sqlSessionFactory;
    static {
        String resource="mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //注意：
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static SqlSession getSqlSession(){
        //true表示开启事务自动提交
        return sqlSessionFactory.openSession(true);
    }
}
