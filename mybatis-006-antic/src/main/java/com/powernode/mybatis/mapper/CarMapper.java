package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {

    //插入Car信息,并使用生成的主键值
    int insertCarUseGeneratedKeys(Car car);

    //根据汽车品牌进行模糊查询
    List<Car> selectByBrandLike(String brand);

    //根据id批量删除
    int deleteBatch(String ids);

    //查询所有汽车信息,根据asc升序或者desc降序
    List<Car> selectAllByAscOrDesc(String ascOrDesc);

    //根据汽车类型获取汽车信息
    List<Car> selectByCarType(String carType);

}
