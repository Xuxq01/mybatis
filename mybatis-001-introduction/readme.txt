开发第一个mybatis程序

1.resource目录:
    放在这个目录当中的的,一般都是资源文件,配置文件
    直接放到resources目录下的资源,等同于放到了类
    的根路径下

2.开发步骤
*第一步:打包方式jar
*第二步:引入依赖
    -mybatis依赖
    -mysql驱动依赖
*第三步:编写MyBatis核心配置文件:mybatis-config.xml
    注意:
        第一,这个文件不是必须叫做mybatis-config.xml,可以使用其他名字
        第二,文件存放的位置不是固定的,可以随意,但一般情况下,会放到类的根路径下

    mybatis-config.xml文件中的配置信息不理解没关系,先把连接数据库的修改即可
    其他的不改
*第四步:编写xxxMapper.xml文件
    在这个配置文件当中编写sql语句
    文件名和放的位置也不是固定的
*第五步:在mybatis-config.xml文件中指定Mapper.xml文件的路径
    <mapper resource="CarMapper.xml"/>
*第六步:编写mybatis程序(使用mybatis的类库,编写mybatis程序,连接数据库做增删改查就行了)
    在mybatis中,负责执行sql语句的对象叫什么?
        SqlSession
    SqlSession是专门用来执行SQL语句的,是一个Java程序和数据库之间的一次会话
    要想获取SqlSession对象,要先获取SqlSessionFactory对象,通过SqlSessionFactory工厂来生产SqlSession对象
    怎么获取SqlSessionFactory对象呢?
        需要先获取SqlSessionFactoryBuilder对象
        通过SqlSessionFactoryBuilder对象的build方法,来获取一个SqlSessionFactory对象

    mybatis的核心对象包括:
        SqlSessionFactoryBuilder
        SqlSessionFactory
        SqlSession

        SqlSessionFactoryBuilder->SqlSessionFactory->SqlSession

3.从 XML 中构建 SqlSessionFactory
    第一,在MyBatis一定有一个很重要的对象,这个对象是,SqlSessionFactory
    第二,SqlSessionFactory对象的创建需要XML

    XML一定是一个配置文件

4.mybatis中有两个主要的配置文件
    其中一个是:mybatis-config.xml,这是核心配置文件,主要配置连接数据库的信息等(一个)
    另一个是:XxxxMapper.xml,这个文件是专门用来编写SQL语句的配置文件(一个表一个)
        t_user表:一般会对应一个UserMapper.xml
        t_student表:一般会对应一个Student Mapper.xml

5.关于第一个程序的小细节
    *mybatis中sql语句的结尾";"可以省略
    *Resources.getResourceAsStream
        小技巧:凡是遇到resource这个单词,大部分情况下,这种加载资源的方式是从类的路径下开始加载(开始查找)
        优点:采用这种方式,从类路径当中加载资源,项目的移植性很强
    *InputStream is = new FileInputStream();
        采用这种方式也可以
        缺点:可以执行差,程序不够健壮,可以回移植到其他操作系统上,导致路径无效,需要修改java代码中的路径,违背了OCP原则
    *已经验证了:
        mybatis核心配置文件的名字不一定是mybatis-config.xml,可以是其他名字
        mybatis核心配置文件的路径,也不一定是在类的根路径下,可以放在其他位置,但为了项目的移植性,最好放在类的根路径下
    *InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        ClassLoader.getSystemClassLoader() 获取系统的类加载器
        他就是从类路径当中加载资源的
    *CarMapper.xml文件的名字是固定的吗?路径是固定的吗?
        都不是固定的
        <mapper resource="CarMapper.xml"/>resource属性,从类路径中加载资源
        <mapper url="file:///D:/CarMapper.xml"></mapper>这种方式是从绝对路径当中加载路径

6.关于mybatis的事务管理机制(深度剖析)
    *在mybatis-config.xml文件中,可以通过以下的配置来进行mybatis的事务管理
        <transactionManager type="JDBC">
    *type属性的值包括两个:
        JDBC(jdbc)
        MANAGED(managed)
        type后面的值,只有这两个可选,不区分大小写
    *在mybatis中提供了两种事务管理机制
        第一种:JDBC事务管理器
        第二种:MANAGED事务管理器
    *JDBC事务管理器:
        mybatis自己管理事务,自己采用原生的JDBC代码去管理事务
            conn.setAutoCommit(false); 开启事务
            ....业务处理....
            conn.commit(); 手动提交事务
        使用JDBC事务管理器的话,底层创建的事务管理器对象:JdbcTransaction对象

        如果编写下面的代码:
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        表示没有开启事务,因为不会执行:conn.setAutoCommit(false);
        在JDBC事务中,没有执行的话autoCommit就是true
        如果是true就表示没有开启事务,执行一条sql语句就提交一次

    *MANAGED事务管理器:
        mybatis不再负责事物的管理了,交给其他容器来负责,例如:spring

        对于我们当前的单纯有mybatis的情况下,如果配置为:MANAGED
        那么事务是没人管的,表示事务没有开启

    *JDBC中的事务:
        如果没有在JDBC中执行:conn.setAutoCommit(false);的话,默认是true

7.关于mybatis继承日志组件,让我们调试起来更加方便
    *mybatis常见的集成的日志组件有哪些呢?
        SLF4J:是一个日志标准,其中有一个框架叫做logback,它实现了该规范
        LOG4J
        LOG4J2
        STDOUT_LOGGING
        ....

    *其中STDOUT_LOGGING是标准日志,mybatis已经实现了这种标准日志,mybatis框架本身已经实现了这种标准
    只要开启即可,在mybatis-config.xml文件中使用setting标签进行配置开启
        <settings>
            <setting name="logImpl" value="STDOUT_LOGGING"/>
        </settings>

        这种实现也是可以的,可以看到一些信息
        但是没有丰富的配置,可以集成第三方的log组件

    *集成logback日志框架:
        logback日志框架实现了slf4j标准
        第一步:引入logback的依赖
        第二步:引入logback所必需的xml配置文件
            这个文件的名字必须叫做: logback.xml或者logback.test.xml,不能是其他的名字
            这个配置文件必须放在类的根路径下,不能是其他位置