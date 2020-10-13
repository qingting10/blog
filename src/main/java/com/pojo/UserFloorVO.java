package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFloorVO {
    private int id;            //用户表id
    private String username;    //用户名
    private int blognum;         //原创博客数
    private int fans;            //粉丝
    private int appreciate;      //点赞
    private int communicate;     //评论
    private int visitedNumber;  //访问量
    private int rownum;         //排名
}
