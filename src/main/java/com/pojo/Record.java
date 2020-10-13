package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
//日志表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private int id;
    private int managerId;
    private String content;
    private Timestamp createtime;
}
