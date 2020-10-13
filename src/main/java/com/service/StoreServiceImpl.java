package com.service;

import com.dao.StoreMapper;
import com.pojo.Store;

import java.util.List;

public class StoreServiceImpl implements StoreService{

    private StoreMapper storeMapper;

    public void setStoreMapper(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    @Override
    public int addStore(Store store) {
        return storeMapper.addStore(store);
    }

    @Override
    public int deleteStoreById(int id) {
        return storeMapper.deleteStoreById(id);
    }

    @Override
    public Store queryStoreByUidAndBidAndscId(int userId, int blogId, int scId) {
        return storeMapper.queryStoreByUidAndBidAndscId(userId, blogId, scId);
    }

    @Override
    public List<Integer> queryBidByUserIdAndscId(int userId, int scId) {
        return storeMapper.queryBidByUserIdAndscId(userId, scId);
    }
}
