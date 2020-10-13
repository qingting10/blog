package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyUserVO {
    //评论表
    private int id;
    private int parentId;
    private int userId;
    private  int blogId;
    private String content;
    private String status;
    private Timestamp replyTime;

    //用户表
    private String avatar;
    private String username;

    //博客表
    private String title;
}
