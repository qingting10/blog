package com.service;

import com.dao.NoticeMapper;
import com.pojo.NoticeUserBlogVO;
import com.pojo.Notices;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper noticeMapper;

    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public int addNotice(Notices notices) {
        return noticeMapper.addNotice(notices);
    }

    @Override
    public int deleteNotice(int id) {
        return noticeMapper.deleteNotice(id);
    }

    @Override
    public int updateNotice(Notices notices) {
        return noticeMapper.updateNotice(notices);
    }

    @Override
    public Notices queryNoticeById(int id) {
        return noticeMapper.queryNoticeById(id);
    }

    @Override
    public List<Notices> queryAllNotice() {
        return noticeMapper.queryAllNotice();
    }

    @Override
    public List<Notices> noticeList(int receiveUserId, String type) {
        return noticeMapper.noticeList(receiveUserId, type);
    }

    @Override
    public List<NoticeUserBlogVO> noticeUnameList(int receiveUserId) {
        return noticeMapper.noticeUnameList(receiveUserId);
    }

    @Override
    public List<NoticeUserBlogVO> noticeUnameTitleList(int receiveUserId, String type) {
        return noticeMapper.noticeUnameTitleList(receiveUserId, type);
    }

    @Override
    public int deleteNoticeByTargetId(int targetId) {
        return noticeMapper.deleteNoticeByTargetId(targetId);
    }

    @Override
    public int deleteNoticeByreceiveUserId(int receiveUserId) {
        return noticeMapper.deleteNoticeByreceiveUserId(receiveUserId);
    }


}
