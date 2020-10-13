package com.dao;

import com.pojo.Storeclass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreclassMapper {

    //增
    int addStoreclass(Storeclass storeclass);

    //删
    int deleteStoreclassById(int id);

    //根据uid 获取所有分类列表
    List<Storeclass> queryStoreListByUid(int uid);

    //根据uid classname 获取对象
    Storeclass queryStoreclassByUidAndClassname(@Param("uid") int uid, @Param("classname")String classname);

    //根据classname获取storeclass对象
//    Storeclass queryStoreclassByCname(String classname);

}
