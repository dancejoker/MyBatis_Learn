package com.wang.Mapper;

import com.wang.po.User;
import com.wang.po.UserCustorm;
import com.wang.po.UserQueryVo;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/6 0006.
 */
public class UserMapperTest extends TestCase {
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
    public void testFindUserById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用userMapper的方法
        User user = userMapper.findUserById(1);
        System.out.println(user);
    }
    //综合查询
    @Test
    public void testFindUserList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustorm userCustorm = new UserCustorm();
  //    由于使用动态sql,如果不设置某个值，条件不会拼接在sql中
  //      userCustorm.setSex("f");
        userCustorm.setUsername("小明");
        List<Integer>  ids = new ArrayList<Integer>();
        ids.add(19);
        ids.add(20);
        ids.add(25);

        userQueryVo.setIds(ids);
        userQueryVo.setUserCustorm(userCustorm);

        //调用userMapper的方法
        List<UserCustorm> user = userMapper.findUserList(userQueryVo);
        System.out.println(user);
    }

    @Test
    public void testFindUserListResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用userMapper的方法
        User user = userMapper.findUserListResultMap(1);
        System.out.println(user);
    }
    @Test
    public void testFindUserByName() throws Exception {

    }
    @Test
    public void testInsertUser() throws Exception {

    }
    @Test
    public void testDeleteUser() throws Exception {

    }
}