package com.service;

import com.dao.UsersMapper;
import com.pojo.Users;

import java.util.List;

public class UserServiceImpl implements UserService {

    //    业务层service调dao层:      要组合dao层

    private UsersMapper usersMapper;

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public int addUser(Users user) {
        return usersMapper.addUser(user);
    }

    @Override
    public int updateUser(Users users) {  return usersMapper.updateUser(users); }

    @Override
    public int deleteUserById(int id) {
        return usersMapper.deleteUserById(id);
    }

    @Override
    public Users queryUserById(int id) {
        return usersMapper.queryUserById(id);
    }

    @Override
    public List<Users> queryAllUsers() {
        return usersMapper.queryAllUsers();
    }

    @Override
    public Users isLoginUser(String username) {
        return usersMapper.isLoginUser(username);
    }

    @Override
    public boolean registerUser(Users users) {
        return false;
    }

    @Override
    public Users queryUserByUsername(String username) {
        return usersMapper.queryUserByUsername(username);
    }

    @Override
    public Users queryUserByBlogid(int id) {
        return usersMapper.queryUserByBlogid(id);
    }

    @Override
    public List<Users> queryUsersMoreBlog() {
        return usersMapper.queryUsersMoreBlog();
    }

    @Override
    public List<Users> orderUser(String whichOne) {
        return usersMapper.orderUser(whichOne);
    }
}
