package com.dao;

import com.pojo.Attention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttentionMapper {
    //增
    int addAttention(Attention attention);

    //删
    int deleteAttentionById(int id);

    //id查
    Attention queryAttentionById(int id);

    //userId    byUserId查
    Attention queryAttenByUidAndBuid(@Param("userId") int userId, @Param("byUserId") int byUserId);

    //根据userId查     attention的集合
    List<Attention> queryAttentionListByUid(int userId);

    //根据byUserId查   attention的集合
    List<Attention> queryAttentListBybyuid(int byUserId);

}
