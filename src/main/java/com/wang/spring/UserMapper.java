package com.wang.spring;

import com.wang.po.User;
import com.wang.po.UserCustorm;
import com.wang.po.UserQueryVo;

import java.util.List;

public interface UserMapper {
    //根据id查询用户信息
    public User findUserById(int id) throws Exception;
}
