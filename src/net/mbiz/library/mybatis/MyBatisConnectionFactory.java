package net.mbiz.library.mybatis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
public class MyBatisConnectionFactory {
 
    private static SqlSessionFactory sqlSessionFactory;
 
    static {
        try {
            String resource = "/resources/config/mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
 
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
        	System.err.println("net.mbiz.library.mybatis.MyBatisConnectionFactory : mybatis-config.xml 파일을 찾을 수 없음.");
            fileNotFoundException.printStackTrace();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}

