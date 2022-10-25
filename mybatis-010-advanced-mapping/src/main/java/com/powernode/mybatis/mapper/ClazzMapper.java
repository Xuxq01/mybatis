package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Clazz;

public interface ClazzMapper {

    //分步查询第一步:根据班级编号获取班级信息
    Clazz selectByStep1(Integer cid);

    //根据班级编号查询班级信息
    Clazz selectByCollection(Integer cid);

    //分步查询第二步:根据cid获取班级信息
    Clazz selectByIdStep2(Integer cid);
}
