package com.powernode.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION:
 * @USER: 11240
 * @DATE: 2022/10/18 8:17
 */
public class ConfigurationTest {

    @Test
    public void testDataResource() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        /*//会话1
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        sqlSession1.insert("car.insertCar");
        sqlSession1.commit();
        sqlSession1.close();//因为要测试连接池的效果,必须关闭

        //会话2
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        sqlSession2.insert("car.insertCar");
        sqlSession2.commit();
        sqlSession2.close();//因为要测试连接池的效果,必须关闭*/

        for (int i = 0; i < 4; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("car.insertCar");
        }
    }

    @Test
    public void testEnvironment() throws IOException {
        //获取SqlSessionFactory对象(采用默认的方式获取)
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        //这种方式就是获取默认的环境
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("car.insertCar");
        sqlSession.commit();
        sqlSession.close();

        //这种方式是通过环境id来使用指定的环境
        SqlSessionFactory sqlSessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"),"powernodeDB");
        SqlSession sqlSession1 = sqlSessionFactory1.openSession();
        sqlSession1.insert("car.insertCar");
        sqlSession1.commit();
        sqlSession1.close();
    }
}
