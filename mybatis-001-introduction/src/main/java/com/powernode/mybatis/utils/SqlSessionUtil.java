package com.powernode.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION: mybatis工具类
 * @USER: 11240
 * @DATE: 2022/10/16 22:11
 */
public class SqlSessionUtil {

    //工具类的构造方法一般都是私有化的
    private SqlSessionUtil(){}

    private static SqlSessionFactory sqlSessionFactory;

    //工具类第一次加载的时候解析mybatis-config.xml文件,创建SqlSessionFactory对象
    static{
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*public static SqlSession openSession(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //SqlSessionFactory对象:一个SqlSessionFactory对应一个environment,一个environment通常是一个数据库
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }*/

    //获取sql会话对象
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }
}
