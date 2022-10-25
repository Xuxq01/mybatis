package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {

    //根据id批量删除,使用or关键字
    int deleteByIds2(@Param("ids") Long[] ids);

    //批量插入,一次插入多条Car信息
    int insertBatch(@Param("cars") List<Car> cars);

    //批量删除:foreach标签
    int deleteByIds(@Param("ids") Long[] ids);

    //使用choose when otherwise标签
    List<Car> selectByChoose(@Param("brand") String brand,@Param("guidePrice") Double guidePrice,@Param("carType") String carType);

    //使用set标签
    int updateBySet(Car car);

    //更新Car
    int updateById(Car car);

    //使用Trim标签
    List<Car> selectByMultiConditionWithTrim
    (@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    //使用where标签,使where字句更加智能
    List<Car> selectByMultiConditionWithWhere
    (@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 多条件查询,品牌,指导价,汽车类型
     */
    List<Car> selectByMultiCondition
    (@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);
}
