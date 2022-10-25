package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Student;

import java.util.List;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION:
 * @USER: 11240
 * @DATE: 2022/10/22 22:54
 */
public interface StudentMapper {

    //根据班级编号查询学生信息
    List<Student> selectByCidStep2(Integer cid);

    //分步查询第一步:先根据学生的sid查询学生的信息
    Student selectByIdStep1(Integer sid);

    //一条sql语句,association
    Student selectByIdAssociation(Integer id);

    //根据id获取学生信息,同时获取学生关联的班级信息
    Student selectById(Integer id);
}
