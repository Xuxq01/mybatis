package com.powernode.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION: 采用正规的方式写一个完整版的mybatis程序
 * @USER: 11240
 * @DATE: 2022/10/16 18:53
 */
public class MyBatisCompleteTest {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            sqlSession = sqlSessionFactory.openSession();
            //执行sql语句,处理相关业务
            int count = sqlSession.insert("insertCar");
            System.out.println(count);
            //执行到这里,没有发生任何异常,提交事务,终止事务
            sqlSession.commit();
        } catch (Exception e) {
            //最好回滚事务
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            //关闭会话
            if (sqlSession == null) {
                sqlSession.close();
            }
        }
    }
}

