package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {

    //增
    int insert(Car car);
    //删
    int deleteById(Long id);
    //改
    int update(Car car);
    //查
    Car selectById(Long id);
    //获取所有的汽车信息
    List<Car> selectAll();
}
