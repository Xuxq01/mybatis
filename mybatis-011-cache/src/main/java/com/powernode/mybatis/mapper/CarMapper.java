package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

public interface CarMapper {

    //测试二级缓存
    Car selectById2(Long id);

    int insertClazz(@Param("cid") Integer cid,@Param("cname") String cname);

    //根据id获取Car信息。
    Car selectById(Long id);

}
