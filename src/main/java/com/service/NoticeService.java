package com.service;

import com.pojo.NoticeUserBlogVO;
import com.pojo.Notices;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeService {

    //    增加通知
    int addNotice(Notices notices);

    //    删除通知
    int deleteNotice( int id);

    //    更新通知
    int updateNotice(Notices notices);

    //    根据id查询通知
    Notices queryNoticeById( int id);

    //    查询所有通知
    List<Notices> queryAllNotice();

    //根据receiveUserId, 查找对应消息（公告/关注/评论/点赞）的list
    List<Notices> noticeList(int receiveUserId, String type);

    //找关注集合（包括username）
    List<NoticeUserBlogVO> noticeUnameList(int receiveUserId);

    //找 点赞/评论集合（包括usname, title）
    List<NoticeUserBlogVO> noticeUnameTitleList(int receiveUserId, String type);

    //根据targetId删除
    int deleteNoticeByTargetId(int targetId);

    //根据receiveUserId删除
    int deleteNoticeByreceiveUserId(int receiveUserId);

}
