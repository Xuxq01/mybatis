package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Log;

import java.util.List;

public interface LogMapper {

    //根据日期查询不同的表获取所有日志
    List<Log> selectAllByTable(String date);

}
