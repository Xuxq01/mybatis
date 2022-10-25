package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.ClazzMapper;
import com.powernode.mybatis.pojo.Clazz;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION:
 * @USER: 11240
 * @DATE: 2022/10/22 22:56
 */
public class ClazzMapperTest {
    @Test
    public void testSelectByStep1(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByStep1(1000);
        //System.out.println(clazz);

        //只访问班级名字
        System.out.println(clazz.getCname());

        System.out.println(clazz.getStus());
        sqlSession.close();
    }
    @Test
    public void testSelectByCollection(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(1000);
        System.out.println(clazz);
        sqlSession.close();
    }
}
