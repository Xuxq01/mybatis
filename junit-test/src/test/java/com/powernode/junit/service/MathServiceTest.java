package com.powernode.junit.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION: 单元测试类
 * @USER: 11240
 * @DATE: 2022/10/16 19:17
 */
public class MathServiceTest {//名字规范:要测试的类名+Test

    //单元测试方法写多少个
    //一般是一个业务方法对应一个测试方式
    //测试方法的规范:public void testXxx(){}
    //测试方法的方法名以test开始,比如测试的方式是sum,这个测试方法名:testSum
    //@Test注解非常重要,被它标注的方法就是一个单元测试方法
    @Test
    public void testSum(){
        //单元测试中有两个重要概念:
        //1.实际值(被测试方法的真正执行结果)
        //2.期望值(执行了业务方法之后你期望的执行结果)
        MathService mathService =  new MathService();
        //获取实际值
        int actual = mathService.sum(1, 2);
        //期望值
        int expected = 3;
        //加断言进行测试
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testSub(){
        MathService mathService = new MathService();
        int actual = mathService.sub(10,5);
        int expected = 5;
        Assert.assertEquals(expected,actual);
    }
}
