package com.wang.spring;

import com.wang.po.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;




public class UserDaoImpl  extends SqlSessionDaoSupport implements UserDao {
    public User findUserById(int id) throws Exception {
        //继承SqlSessionDaoSupport,通过 this.getSqlSession()获取sqlSession
        SqlSession sqlSession = this.getSqlSession();
        User user = sqlSession.selectOne("test.findUserById",id);
        return user;
    }




}
