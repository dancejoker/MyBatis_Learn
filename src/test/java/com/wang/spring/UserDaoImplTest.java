package com.wang.spring;

import com.wang.Dao.*;
import com.wang.Dao.UserDaoImpl;
import com.wang.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by pc-wang on 2017/5/28.
 */
public class UserDaoImplTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
    }
    @Test
    public void testfindUserById() throws Exception{
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        //调用userDao的方法
        User userById = userDao.findUserById(1);
        System.out.println(userById);
    }

    @Test
    public  void testfindMapperUserById() throws Exception{
            UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User userById = userMapper.findUserById(1);
        System.out.println(userById);
    }
}