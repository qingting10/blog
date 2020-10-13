package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogsVO {
    private int id;
    private int userId;     //用户id
    private String username;
    private String avatar;
    //以下都是博客的字段
    private String title;   //博客标题
    private String content; //博客内容
    private Timestamp updateTime;   //更新时间
    private int appreciate;     //点赞数
    private int communicate;    //评论数
    private int visitedNumber;  //访问量
    private String status;//    状态（被举报/正常）
    private Integer cid;   //分类名id
    private String classname;
}
