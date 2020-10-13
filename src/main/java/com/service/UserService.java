package com.service;

import com.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
//用户表的 业务层
public interface UserService {
    //增加一个用户
    int addUser(Users user);

    //删除一个用户
    int deleteUserById(int id);

    //更新用户
    int updateUser(Users users);

    //查询一个用户
    Users queryUserById(int id);

    //查询所有用户
    List<Users> queryAllUsers();

    //    判断登录
    Users isLoginUser(String username);

    //    用户注册
    boolean registerUser(Users users);

    //  查询用户by  username
    Users queryUserByUsername(String username);

    //    通过博客id获取用户
    Users queryUserByBlogid(@Param("id") int id);

    //  找出博客数最多的10个人
    List<Users> queryUsersMoreBlog();

    //对博客数排序
    List<Users> orderUser(String whichOne);



}
