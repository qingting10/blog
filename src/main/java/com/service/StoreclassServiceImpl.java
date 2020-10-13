package com.service;

import com.dao.StoreclassMapper;
import com.pojo.Storeclass;

import java.util.List;

public class StoreclassServiceImpl implements StoreclassService {
    private StoreclassMapper storeclassMapper;

    public void setStoreclassMapper(StoreclassMapper storeclassMapper) {
        this.storeclassMapper = storeclassMapper;
    }

    @Override
    public int addStoreclass(Storeclass storeclass) {
        return storeclassMapper.addStoreclass(storeclass);
    }

    @Override
    public int deleteStoreclassById(int id) {
        return storeclassMapper.deleteStoreclassById(id);
    }

    @Override
    public List<Storeclass> queryStoreListByUid(int uid) {
        return storeclassMapper.queryStoreListByUid(uid);
    }

    @Override
    public Storeclass queryStoreclassByUidAndClassname(int uid, String classname) {
        return storeclassMapper.queryStoreclassByUidAndClassname(uid, classname);
    }

//    @Override
//    public Storeclass queryStoreclassByCname(String classname) {
//        return storeclassMapper.queryStoreclassByCname(classname);
//    }
}
