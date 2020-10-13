package com.pojo;
//点赞表

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appreciate {
    private int id;
    private int blogId;
    private int userId;
    private  int uid;
    private Timestamp createtime;
}
