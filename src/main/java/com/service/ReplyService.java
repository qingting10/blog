package com.service;

import com.pojo.Reply;
import com.pojo.ReplyUserVO;
import com.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyService {
    //    增
    int addReply(Reply reply);

    //    删
    int deleteReply(int id);

    //    改
    int updateReply(Reply reply);

    //    根据id查
    Reply queryReplyById(int id);

    //    查全部
    List<ReplyUserVO> queryAllReply();

    //根据博客id  获取所有博客一级评论
    List<ReplyUserVO> queryFirstReplyByBid(int blogId);

    //根据parentId  blogId，查找评论
    List<ReplyUserVO> querySecondReplyByPidAndBid(int parentId, int blogId);

    //根据id  删除评论以及它的子评论（parendId==id）
    int deleteReplyByidAndPid(int id, int parentId);

    //根据parentId获取被回复者的username，和avatar
    Users queryLastReply(int id);

    //    找所有被举报评论
    List<ReplyUserVO> statusList();

    //设置被举报
    int setNormal(int id);
    //设置撤销举报
    int cancelStatus(int id);

    //根据userid删除
    int deleteReplyByUserid(int userId);

    //根据username 查评论
    List<ReplyUserVO> replyvoListByUsername(String username);



}
