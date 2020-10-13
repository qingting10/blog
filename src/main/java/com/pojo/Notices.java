package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//通知表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notices {
    private int id;
    private int sendUserId;     //发送消息的用户id
    private int receiveUserId;  //接收消息的用户id
    private String type;        //消息类型
    private int targetId;       //目标id（文章id/评论id）
    private String content;     //消息内容
    private String status;      //已读未读
    private Timestamp sendTime; //发送时间
}
