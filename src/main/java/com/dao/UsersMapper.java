package com.dao;

import com.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    //增加一个用户
    int addUser(Users user);

    //删除一个用户            @Param注解单一属性，后面的xml可使用#{}或${}方式
    int deleteUserById(@Param("id") int id);

    //更新用户
    int updateUser(Users users);

    //查询一个用户
    Users queryUserById(@Param("id") int id);

    //查询所有用户
    List<Users> queryAllUsers();

    //  查询用户by  username
    Users queryUserByUsername(String username);


//    判断登录
    Users isLoginUser(String username);


//    通过博客id获取用户
    Users queryUserByBlogid(@Param("id") int id);

//  找出博客数最多的10个人
    List<Users> queryUsersMoreBlog();

    //对博客数排序
    List<Users> orderUser(String whichOne);



}
