package com.service;

import com.dao.BlogMapper;
import com.pojo.Blogs;
import com.pojo.BlogsVO;

import java.util.List;

public class BlogServiceImpl implements BlogService {
    //调用dao层的操作，设置一个set接口，方便spring管理

    private BlogMapper blogMapper;

    public void setBlogMapper(BlogMapper blogMapper){
        this.blogMapper = blogMapper;
    }


    @Override
    public int addBlog(Blogs blogs) { return blogMapper.addBlog(blogs);}

    @Override
    public int deleteBlogById(int id) {
        return blogMapper.deleteBlogById(id);
    }

    @Override
    public int updateBlog(Blogs blogs) { return blogMapper.updateBlog(blogs);}


    @Override
    public Blogs queryBlogById(int id) {
        return blogMapper.queryBlogById(id);
    }

    @Override
    public List<BlogsVO> queryAllBlog() {
        return blogMapper.queryAllBlog();
    }

    @Override
    public List<BlogsVO> queryBlogByTitle(String title) {
        return blogMapper.queryBlogByTitle(title);
    }

    @Override
    public BlogsVO queryUnameAndBlogByBid(int id) {
        return blogMapper.queryUnameAndBlogByBid(id);
    }

    @Override
    public List<Blogs> queryBlogsByUserId(int userId) {
        return blogMapper.queryBlogsByUserId(userId);
    }

    @Override
    public List<BlogsVO> queryBlogByTitleLike(String title) {
        return blogMapper.queryBlogByTitleLike(title);
    }

    @Override
    public List<Blogs> queryListByTimelast(int userId) {
        return blogMapper.queryListByTimelast(userId);
    }

    @Override
    public List<Blogs> queryListByVisitnum(int userId) {
        return blogMapper.queryListByVisitnum(userId);
    }

    @Override
    public Integer countCid(int cid) {
        return blogMapper.countCid(cid);
    }

    @Override
    public List<Blogs> hotBlogList(int userId) {
        return blogMapper.hotBlogList(userId);
    }

    @Override
    public List<Blogs> statusList() {
        return blogMapper.statusList();
    }

    @Override
    public int deleteBlogByUserId(int userId) {
        return blogMapper.deleteBlogByUserId(userId);
    }

    @Override
    public int cancelStatus(int id) {
        return blogMapper.cancelStatus(id);
    }

    @Override
    public int putStatus(int id) {
        return blogMapper.putStatus(id);
    }

    @Override
    public List<BlogsVO> blogCnameListByCidAndUid(int cid, int userId) {
        return blogMapper.blogCnameListByCidAndUid(cid, userId);
    }

}
