package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private int id;
    private int blogId;
    private int userId;
    private Timestamp storetime;
    private int scId;
}
