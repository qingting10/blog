package com.service;

import com.pojo.Appreciate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppreciateService {
    //增
    int addAppreciate(Appreciate appreciate);

    //删
    int deleteAppreciateById(int id);

    //改
    int updateAppreciate(Appreciate appreciate);

    //id查
    Appreciate queryAppreciateById(int id);

    //查全部
    List<Appreciate> queryAllAppreciate();

    //根据uid bid查
    Appreciate queryAppreciateByUidAndBid(@Param("userId")int userId, @Param("blogId")int blogId);

    //    删除对应blogid的记录
    int deleteApplist(int blogId);
}
