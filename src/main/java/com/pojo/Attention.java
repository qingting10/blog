package com.pojo;

//关注表实体类
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attention {
    private int id;    //实体类id
    private int userId;
    private int ByUserId;
    private Date attentionTime;
}
