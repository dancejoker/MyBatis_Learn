package com.wang.Mapper;

import com.wang.po.OrderCustomer;
import com.wang.po.Orders;
import com.wang.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


/**
 * Created by pc-wang on 2017/5/16.
 */
public class OrdersMapperCustomerTest {
    SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void testFindOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustomer ordersMapperCustomer = sqlSession.getMapper(OrdersMapperCustomer.class);
        List<OrderCustomer> orderUser = ordersMapperCustomer.findOrderUser();
        System.out.println(orderUser);
        sqlSession.close();
    }

    @Test
    public void testFindOrderUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustomer ordersMapperCustomer = sqlSession.getMapper(OrdersMapperCustomer.class);
        List<Orders> orderUser = ordersMapperCustomer.findOrderUserResultMap();
        System.out.println(orderUser);
        sqlSession.close();
    }

    @Test
    public void testFindOrdersAndOrderDetailResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustomer ordersMapperCustomer = sqlSession.getMapper(OrdersMapperCustomer.class);
        List<Orders> orderUser = ordersMapperCustomer.findOrdersAndOrderDetailResultMap();
        System.out.println(orderUser);
        sqlSession.close();
    }

    @Test
    public void testFindUserAndItemsResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustomer ordersMapperCustomer = sqlSession.getMapper(OrdersMapperCustomer.class);
        List<User> orderUser = ordersMapperCustomer.findUserAndItemsResultMap();
        System.out.println(orderUser);
        sqlSession.close();
    }

    //查询订单关联查询用户，用户信息延迟加载
    @Test
    public void testFindOrderUserLazyLoading() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustomer ordersMapperCustomer = sqlSession.getMapper(OrdersMapperCustomer.class);
        List<Orders> orderUserLazyLoading = ordersMapperCustomer.findOrderUserLazyLoading();
        for(Orders order : orderUserLazyLoading){
            User user  = order.getUser();
            System.out.println(order);
        }
    }

    @Test
    public void testCacheOne() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper usermapper = sqlSession.getMapper(UserMapper.class);
        //下面使用一个sqlSession
        User userById = usermapper.findUserById(1);
        System.out.println(userById);
        //如果sqlSession去执行commit操作（执行插入，更新，删除），清空SqlSession中的
        //一级缓存，这样做为了让缓存中的信息是最新的信息，避免脏读
        userById.setUsername("特朗普");
        usermapper.updateUser(userById);
        sqlSession.commit();
        User userById2 = usermapper.findUserById(1);
        System.out.println(userById2);

        sqlSession.close();
    }

    @Test
    public void testCacheTwo() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        UserMapper usermapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper usermapper2 = sqlSession2.getMapper(UserMapper.class);
        UserMapper usermapper3 = sqlSession3.getMapper(UserMapper.class);
        //下面使用一个sqlSession
        User userById1= usermapper1.findUserById(1);
        System.out.println(userById1);
        //这里我们执行关闭操作，将sqlSession中的数据写到二级缓存中去。
        sqlSession1.close();

        //使用sqlSession3执行提交操作
        User userById3 = usermapper3.findUserById(1);
        userById3.setUsername("奥巴马");
        usermapper3.updateUser(userById3);
        //执行提交，清空UserMapper下的的二级缓存
        sqlSession3.commit();
        sqlSession3.close();

        User userById2 = usermapper2.findUserById(1);
        System.out.println(userById2);
        sqlSession2.close();
    }


}