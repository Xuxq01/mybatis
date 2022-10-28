package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarMapper {

    //查询所有的Car，通过分页查询插件PageHelper完成。
    List<Car> selectAll();

    List<Car> selectByPage(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
}

