package com.wang.Mapper;

import com.wang.po.User;
import com.wang.po.UserCustorm;
import com.wang.po.UserQueryVo;

import java.util.List;

/**
 * 程序员还需要编写mapper.xml映射文件
 * 程序员编写mapper接口需要遵循一些开发规范，mybatis可以自动生成mapper接口实现类代理对象。
 * 开发规范：
 * 1、在mapper.xml中namespace等于mapper接口地址
 * 2、mapper.java接口中的方法名和mapper.xml中statement的id一致
 * 3、mapper.java接口中的方法输入参数类型和mapper.xml中statement的parameterType指定的类型一致。
 * 4、mapper.java接口中的方法返回值类型和mapper.xml中statement的resultType指定的类型一致。
 * <p>
 * 总结：
 * 以上开发规范主要是对下边的代码进行统一生成：
 * <p>
 * User user = sqlSession.selectOne("test.findUserById", id);
 * sqlSession.insert("test.insertUser", user);
 * ....
 * <p>
 * 问题总结
 * 1.代理对象内部调用selectOne或selectList
 * 如果mapper方法返回单个pojo对象（非集合对象），代理对象内部通过selectOne查询数据库。
 * 如果mapper方法返回集合对象，代理对象内部通过selectList查询数据库。
 * 2.mapper接口方法参数只能有一个，系统是否不利于扩展维护。
 * 系统框架中，dao层的代码是被业务层公用的。
 * 即使mapper接口只有一个参数，可以使用包装类型的pojo满足不同的业务方法的需求。
 */
public interface UserMapper {
    //根据id查询用户信息
    public User findUserById(int id) throws Exception;

    //根据用户名列查询用户列表
    //如果mapper方法返回单个pojo对象（非集合对象），代理对象内部通过selectOne查询数据库。
    //如果mapper方法返回集合对象，代理对象内部通过selectList查询数据库。
    public List<User> findUserByName(String name) throws Exception;

    //添加用户信息
    public void insertUser(User user) throws Exception;

    //删除用户信息
    public void deleteUser(int id) throws Exception;

    //用户信息综合查询
    public List<UserCustorm> findUserList(UserQueryVo userQueryVo) throws Exception;

    //根据id查询用户信息，使用resultMap输出
    public User findUserListResultMap(int id) throws Exception;

    //更新用户信息
    public void updateUser(User user) throws  Exception;
}
