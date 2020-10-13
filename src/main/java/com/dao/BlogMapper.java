package com.dao;

import com.pojo.Blogs;
import com.pojo.BlogsVO;
import com.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BlogMapper {

    //增加一个用户
    int addBlog(Blogs blogs);

    //根据id删除一个博客
    int deleteBlogById(int id);

    //更新blog
    int updateBlog(Blogs blogs);

    //根据id查询，返回博客
    Blogs queryBlogById(int id);

    //查询全部Book，返回list集合
    List<BlogsVO> queryAllBlog();

//    根据博客标题查询博客
    List<BlogsVO> queryBlogByTitle(String title);

    //根据bid查blog和username
    BlogsVO queryUnameAndBlogByBid(@Param("id") int id);

    //根据userId，查询所有博客
    List<Blogs> queryBlogsByUserId(int userId);

    //根据博客标题模糊查询
    List<BlogsVO> queryBlogByTitleLike(String title);

    //按创建时间排序   倒叙
    List<Blogs> queryListByTimelast(int userId);

    //按创建时间排序   降叙
    List<Blogs> queryListByVisitnum(int userId);

    //获取cid对应的blog数量
    Integer countCid(int cid);

    //按倒叙   阅读数
    List<Blogs> hotBlogList(int userId);

    //找所有状态集合
    List<Blogs> statusList();

    //删除博客  根据userId
    int deleteBlogByUserId(int userId);

    //撤销举报
    int cancelStatus(int id);

//    博客举报
    int putStatus(int id);

    //根据cid uid查博客列表
    List<BlogsVO> blogCnameListByCidAndUid(@Param("cid") int cid, @Param("userId") int userId);

}
