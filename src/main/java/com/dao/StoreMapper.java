package com.dao;

import com.pojo.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreMapper {
    //增
    int addStore(Store store);

    //删
    int deleteStoreById(int id);

    //查询数据已存在
    Store queryStoreByUidAndBidAndscId(@Param("userId") int userId, @Param("blogId") int blogId, @Param("scId") int scId);

    //根据userId  scId查找blogId
    List<Integer> queryBidByUserIdAndscId(@Param("userId") int userId, @Param("scId") int scId);




}
