package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//分类表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classify {
    private int id;
    private int uid;
    private String  classname;
    private Timestamp createtime;
}
