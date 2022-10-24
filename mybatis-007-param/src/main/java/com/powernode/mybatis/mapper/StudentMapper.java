package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentMapper {

    //@param注解
    List<Student> selectByNameAndSex2(@Param("name") String name,@Param("sex") Character sex);

    //如果是多个参数的话,mybatis框架会自动创建一个map集合,并且map集合会以以下方式存储参数
        //map.put("arg0",name);
        //map.put("arg1",sex);
        //map.put("param1",name);
        //map.put("param2",sex);
    //根据name和sex查询学生信息
    List<Student> selectByNameAndSex(String name,Character sex);
    //保存学生信息通过pojo参数,是单个参数但不是简单类型
    int insertStudentByPojo(Student student);

    //通过map参数保存学生信息
    int insertStudentByMap(Map<String,Object> map);

    //当接口中的方法的参数只有一个(单个参数),并且参数的数据类型都是简单类型
    //根据id/name/birth查询
    List<Student> selectById(Long id);
    List<Student> selectByName(String name);
    List<Student> selectByBirth(Date birth);
    List<Student> selectBySex(Character sex);

}
