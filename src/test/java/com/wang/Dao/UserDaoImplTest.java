package com.wang.Dao;

import com.wang.po.User;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/5/6 0006.
 */
public class UserDaoImplTest extends TestCase {
    SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws Exception {
        // mybatis配置文件
        String resource = "SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
    }
    @Test
    public void testfindUserById() throws Exception{
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById(1);
        System.out.println(user);
    }

    @Test
    public void testfindUserByName() throws Exception {
        UserDaoImpl userDao = new UserDaoImpl(sqlSessionFactory);
        List<User> list = userDao.findUserByName("小明");
        for(User user : list){
            System.out.println(user);
        }
    }
}