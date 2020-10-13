package com.service;

import com.dao.AppreciateMapper;
import com.pojo.Appreciate;

import java.util.List;

public class AppreciateServiceImpl implements AppreciateService {
    private AppreciateMapper appreciateMapper;

    public void setAppreciateMapper(AppreciateMapper appreciateMapper) {
        this.appreciateMapper = appreciateMapper;
    }

    @Override
    public int addAppreciate(Appreciate appreciate) {
        return appreciateMapper.addAppreciate(appreciate);
    }

    @Override
    public int deleteAppreciateById(int id) {
        return appreciateMapper.deleteAppreciateById(id);
    }

    @Override
    public int updateAppreciate(Appreciate appreciate) {
        return appreciateMapper.updateAppreciate(appreciate);
    }

    @Override
    public Appreciate queryAppreciateById(int id) {
        return appreciateMapper.queryAppreciateById(id);
    }

    @Override
    public List<Appreciate> queryAllAppreciate() {
        return appreciateMapper.queryAllAppreciate();
    }

    @Override
    public Appreciate queryAppreciateByUidAndBid(int userId, int blogId) {
        return appreciateMapper.queryAppreciateByUidAndBid(userId, blogId);
    }

    @Override
    public int deleteApplist(int blogId) {
        return appreciateMapper.deleteApplist(blogId);
    }
}
