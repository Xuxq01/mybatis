package com.powernode.mybatis.test;

import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION:
 * @USER: 11240
 * @DATE: 2022/10/17 16:30
 */
public class CarMapperTest {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //根据id查询一定是一条返回结果
        List<Object> cars = sqlSession.selectList("aaaaaaaa.selectAll");
        //遍历
        cars.forEach(car -> System.out.println(car));
        sqlSession.close();
    }

    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //根据id查询一定是一条返回结果
        Object car = sqlSession.selectOne("selectById", 1);
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //准备数据
        Car car = new Car(4l,"9999","凯美瑞",30.3,"1999-10-11","燃油车");
        //执行sql
        int count = sqlSession.update("updateById", car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //执行sql
        int count = sqlSession.delete("deleteById", 14);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testInsertCarByPojo(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //封装数据
        Car car = new Car(null,"3333","兰博基尼",550.0,"2020-11-11","燃油车");
        //执行sql
        int count = sqlSession.insert("insertCar",car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCar(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //这个对象先使用map集合进行封装
        //前端传过来数据封装到map中
        Map<String,Object> map = new HashMap<>();
        map.put("carNum","1111");
        map.put("brand","比亚迪汉2");
        map.put("guidePrice",10.0);
        map.put("produceTime","2020-11-11");
        map.put("carType","电车");

        //执行sql语句
        //insert方法的参数:
        //第一个参数:sqlId,从CarMapper.xml文件中复制
        //第二个参数:封装数据的对象
        int count = sqlSession.insert("insertCar",map);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
