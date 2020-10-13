package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//用户表 实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private int id;            //用户表id
    private String username;    //用户名
    private String password;    //密码
    private String avatar;      //头像
    private String role;        //角色
    private String email;       //邮箱
    private int blognum;         //原创博客数
    private int fans;            //粉丝
    private int appreciate;      //点赞
    private int communicate;     //评论
    private int visitedNumber;  //访问量
    private Timestamp createTime;   //创建时间

}
