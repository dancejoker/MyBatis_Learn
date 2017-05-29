package com.wang.jdbc;

import java.sql.*;

/**
 * Created by Administrator on 2017/5/1 0001.
 * jdbc问题总结；
 * 1.数据库连接，使用时就创建，不使用就释放，对数据库进行频繁连接开启和而释放。
 *   造成数据库资源浪费，影响了数据库的性能
 * 解决方案:使用数据库连接池管理数据库连接
 * 2.将sql语句硬编码到java代码中，如果sql语句修改，需要重新编译java代码，不利于
 *   系统的维护
 * 解决方案：将sql语句配置到xml配置文件中，即使sql变化
 * 3.向preparedStatement中设置参数，对占位符号位置和设置参数值，硬编码在java代
 *   码中，不利于系统维护
 * 解决方案：将sql语句及占位符号和参数全部配置在xml中。
 * 4.从resultSet中遍历结果集数据时，存在硬编码，获取表的字段进行硬编码，不利于
 *   系统维护。
 * 设想：将查询的结果集，自动映射成java对象。
 */
public class JdbcTest {
    public static void main(String[] args) {
        //数据库连接
        Connection connection = null;
        //预编译的Statement(操作数据库)，使用预编译的Statement提高数据库性能
        //预编译Statement发送SQL语句到数据库端，数据库端会编译SQL语句，然后将其
        //存入缓存中再执行，当有下次有相同的语句出现时，则不需要再编译，这就是预编译Statement
        PreparedStatement preparedStatement = null;
        //结果 集
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "wangqi123");
            //定义sql语句 ?表示占位符
            String sql = "select * from user where username = ?";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "王五");
            //向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //遍历查询结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + "  " + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //倒着释放资源，先是resultSet，再是preparedStatement,然后是connection
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
