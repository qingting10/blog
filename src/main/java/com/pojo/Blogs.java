package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//博客表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blogs {
    private int id;
    private int userId;     //用户id
    private String title;   //博客标题
    private String content; //博客内容
    private Timestamp updateTime;   //更新时间
    private int appreciate;     //点赞数
    private int communicate;    //评论数
    private int visitedNumber;  //访问量
    private String status;//    状态（被举报/正常）
    private Integer cid;   //分类名id
}
