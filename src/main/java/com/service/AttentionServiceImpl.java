package com.service;

import com.dao.AttentionMapper;
import com.pojo.Attention;

import java.util.List;

public class AttentionServiceImpl implements AttentionService {
    private AttentionMapper attentionMapper;

    public void setAttentionMapper(AttentionMapper attentionMapper) {
        this.attentionMapper = attentionMapper;
    }

    @Override
    public int addAttention(Attention attention) {
        return attentionMapper.addAttention(attention);
    }

    @Override
    public int deleteAttentionById(int id) {
        return attentionMapper.deleteAttentionById(id);
    }

    @Override
    public Attention queryAttentionById(int id) {
        return attentionMapper.queryAttentionById(id);
    }

    @Override
    public Attention queryAttenByUidAndBuid(int userId, int byUserId) {
        return attentionMapper.queryAttenByUidAndBuid(userId, byUserId);
    }

    @Override
    public List<Attention> queryAttentionListByUid(int userId) {
        return attentionMapper.queryAttentionListByUid(userId);
    }

    @Override
    public List<Attention> queryAttentListBybyuid(int byUserId) {
        return attentionMapper.queryAttentListBybyuid(byUserId);
    }
}
