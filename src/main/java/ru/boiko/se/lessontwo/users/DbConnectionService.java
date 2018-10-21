package ru.boiko.se.lessontwo.users;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public abstract class DbConnectionService {
    private static final String RESOURCE = "mybatis-config.xml";

    final SqlSessionFactory sqlSessionFactory;

    final SqlSession sqlSession;

    protected DbConnectionService() throws IOException {
        final InputStream inputStream = Resources.getResourceAsStream(RESOURCE);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSession = sqlSessionFactory.openSession();
    }
}
