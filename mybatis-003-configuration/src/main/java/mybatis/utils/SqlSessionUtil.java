package mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @PROJECT_NAME: mybatis
 * @DESCRIPTION:
 * @USER: 11240
 * @DATE: 2022/10/17 16:11
 */
public class SqlSessionUtil {
    private SqlSessionUtil(){};

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }
}
