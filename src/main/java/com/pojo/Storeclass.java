package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storeclass {
    private int id;
    private int uid;
    private String classname;
    private Timestamp createtime;
}
