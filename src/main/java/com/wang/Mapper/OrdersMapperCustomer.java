package com.wang.Mapper;

import com.wang.po.OrderCustomer;
import com.wang.po.Orders;
import com.wang.po.User;

import java.util.List;

/**
 * Created by pc-wang on 2017/5/16.
 */
public interface OrdersMapperCustomer {
    //使用resultType查询
    public List<OrderCustomer> findOrderUser() throws Exception;
    //使用resultMap查询
    public List<Orders> findOrderUserResultMap() throws Exception;
    //查询订单以及订单明细
    public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;
    //查询用户购买的商品信息
    public List<User> findUserAndItemsResultMap() throws  Exception;
    //查询订单关联查询用户，用户信息是延迟加载
    public List<Orders> findOrderUserLazyLoading() throws Exception;
}
