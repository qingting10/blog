package com.service;

import com.dao.ReplyMapper;
import com.pojo.Reply;
import com.pojo.ReplyUserVO;
import com.pojo.Users;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {

    private ReplyMapper replyMapper;

    public void setReplyMapper(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    @Override
    public int addReply(Reply reply) {
        return replyMapper.addReply(reply);
    }

    @Override
    public int deleteReply(int id) {
        return replyMapper.deleteReply(id);
    }

    @Override
    public int updateReply(Reply reply) {
        return replyMapper.updateReply(reply);
    }

    @Override
    public Reply queryReplyById(int id) {
        return replyMapper.queryReplyById(id);
    }

    @Override
    public List<ReplyUserVO> queryAllReply() {
        return replyMapper.queryAllReply();
    }

    @Override
    public List<ReplyUserVO> queryFirstReplyByBid(int blogId) {
        return replyMapper.queryFirstReplyByBid(blogId);
    }

    @Override
    public List<ReplyUserVO> querySecondReplyByPidAndBid(int parentId, int blogId) {
        return replyMapper.querySecondReplyByPidAndBid(parentId,blogId);
    }

    @Override
    public int deleteReplyByidAndPid(int id, int parentId) {
        return replyMapper.deleteReplyByidAndPid(id, parentId);
    }

    @Override
    public Users queryLastReply(int id) {
        return replyMapper.queryLastReply(id);
    }

    @Override
    public List<ReplyUserVO> statusList() {
        return replyMapper.statusList();
    }

    @Override
    public int setNormal(int id) {
        return replyMapper.setNormal(id);
    }

    @Override
    public int cancelStatus(int id) {
        return replyMapper.cancelStatus(id);
    }

    @Override
    public int deleteReplyByUserid(int userId) {
        return replyMapper.deleteReplyByUserid(userId);
    }

    @Override
    public List<ReplyUserVO> replyvoListByUsername(String username) {
        return replyMapper.replyvoListByUsername(username);
    }
}
