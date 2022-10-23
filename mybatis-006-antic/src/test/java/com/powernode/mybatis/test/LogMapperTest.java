package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.LogMapper;
import com.powernode.mybatis.pojo.Log;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION:
 * @USER: 11240
 * @DATE: 2022/10/20 11:02
 */
public class LogMapperTest {

    @Test
    public void testSelectAllByTable(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        LogMapper mapper = sqlSession.getMapper(LogMapper.class);
        List<Log> logs = mapper.selectAllByTable("20221020");
        logs.forEach(log -> System.out.println(log));
        sqlSession.close();
    }

}
