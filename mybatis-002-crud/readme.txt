使用mybatis完成CRUD

1.什么是CRUD:
    C:Create增
    R:Retrieve查
    U:Update改
    D:Delete删

2.insert
    <insert id="insertCar">
        insert into t_car(id,car_num,brand,guide_price,produce_time,car_type)
        values(null,'1003',"丰田霸道",30.0,'2020-10-11','燃油车')
    </insert>
    这样写的问题?
        值不是动态的
        实际中是不存在的
        一定是前端form表单提交数据,然后传给sql语句

    java程序中使用Map可以给SQL语句的占位符传值
        Map<String,Object> map = new HashMap<>();
        map.put("k1","1111");
        map.put("k2","比亚迪汉");
        map.put("k3",10.0);
        map.put("k4","2020-11-11");
        map.put("k5","电车");

        insert into t_car(id,car_num,brand,guide_price,produce_time,car_type) values(null,#{k1},#{k2},#{k3},#{k4},#{k5});
        注意:#{}里写map的key,如果不存在则为null

    java程序中使用POJO类的SQL语句的占位符传值
        Car car = new Car(null,"3333","兰博基尼",550.0,"2020-11-11","燃油车");

        注意:占位符的#{}里写pojo类的属性名
        insert  into t_car(id,car_num,brand,guide_price,produce_time,car_type) values(null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType})

3. delete
    * 需求：根据id删除数据
        将id=13的数据删除。

    实现：
        int count = sqlSession.delete("deleteById", 59);
        <delete id="deleteById">
            delete from t_car where id = #{asbdqw}
        </delete>
        注意：如果占位符只有一个，那么#{}的大括号里可以随意。但是最好见名知意。

4. update
    * 需求：根据id修改某条记录。

    实现：
        <update id="updateById">
            update t_car set
                 car_num=#{carNum},
                 brand=#{brand},
                 guide_price=#{guidePrice},
                 produce_time=#{produceTime},
                 car_type=#{carType}
            where
                id = #{id}
        </update>

        Car car = new Car(4L, "9999", "凯美瑞", 30.3, "1999-11-10", "燃油车");
        int count = sqlSession.update("updateById", car);

5. select（查一个，根据主键查询的话，返回的结果一定是一个。）
    * 需求：根据id查询。

    实现：
        <select id="selectById" resultType="com.powernode.mybatis.pojo.Car">
            select * from t_car where id = #{id}
        </select>

        Object car = sqlSession.selectOne("selectById", 1);

    需要特别注意的是：
        select标签中resultType属性，这个属性用来告诉mybatis，查询结果集封装成什么类型的java对象。你需要告诉mybatis。
        resultType通常写的是：全限定类名。

    Car{id=1, carNum='null', brand='宝马520Li', guidePrice=null, produceTime='null', carType='null'}
    输出结果有点不对劲：
        id和brand属性有值。
        其他属性为null。
            carNum以及其他的这几个属性没有赋上值的原因是什么？
                select * from t_car where id = 1
                执行结果：
                +----+---------+-----------+-------------+--------------+----------+
                | id | car_num | brand     | guide_price | produce_time | car_type |
                +----+---------+-----------+-------------+--------------+----------+
                |  1 | 1001    | 宝马520Li |       10.00 | 2020-10-11   | 燃油车   |
                +----+---------+-----------+-------------+--------------+----------+
                car_num、guide_price、produce_time、car_type这是查询结果的列名。
                这些列名和Car类中的属性名对不上。
                Car类的属性名：
                carNum、guidePrice、produceTime、carType

                那这个问题怎么解决呢？
                    select语句查询的时候，查询结果集的列名是可以使用as关键字起别名的。

                <select id="selectById" resultType="com.powernode.mybatis.pojo.Car">
                    select
                        id,car_num as carNum,brand,guide_price as guidePrice,
                        produce_time as produceTime,
                        car_type as carType
                    from
                        t_car
                    where
                        id = #{id}
                </select>
                起别名之后：
                +----+--------+-----------+------------+-------------+---------+
                | id | carNum | brand     | guidePrice | produceTime | carType |
                +----+--------+-----------+------------+-------------+---------+
                |  1 | 1001   | 宝马520Li |      10.00 | 2020-10-11  | 燃油车  |
                +----+--------+-----------+------------+-------------+---------+

6. select（查所有的）

    <select id="selectAll" resultType="com.powernode.mybatis.pojo.Car">
        select
            id,car_num as carNum,brand,guide_price as guidePrice,
            produce_time as produceTime,
            car_type as carType
        from
            t_car
    </select>

    List<Object> cars = sqlSession.selectList("selectAll");
        注意：resultType还是指定要封装的结果集的类型。不是指定List类型，是指定List集合中元素的类型。
        selectList方法：mybatis通过这个方法就可以得知你需要一个List集合。它会自动给你返回一个List集合。

7. 在sql mapper.xml文件当中有一个namespace,这个属性是用来指定命名空间的。用来防止id重复。
怎么用？
    在xml文件中：
        <mapper namespace="aaaaaaaaa">
            <select id="selectAll" resultType="com.powernode.mybatis.pojo.Car">
                select
                    id,car_num as carNum,brand,guide_price as guidePrice,
                    produce_time as produceTime,
                    car_type
                from
                    t_car
            </select>
        </mapper>
    在java程序中的写法：
        List<Object> cars = sqlSession.selectList("aaaaaaaaa.selectAll");

    实际上，本质上，mybatis中的sqlId的完整写法：
        namespace.id
