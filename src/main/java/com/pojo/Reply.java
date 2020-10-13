package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
//回复表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private int id;
    private int parentId;
    private int userId;
    private  int blogId;
    private String content;
    private String status;
    private Timestamp replyTime;
}
