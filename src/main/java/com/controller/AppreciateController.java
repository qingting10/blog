package com.controller;

import com.pojo.Appreciate;
import com.pojo.Blogs;
import com.pojo.Users;
import com.service.AppreciateService;
import com.service.BlogService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/appreciate")
public class AppreciateController {
    @Autowired
    @Qualifier("AppreciateServiceImpl")
    private AppreciateService appreciateService;

    @Autowired
    @Qualifier("BlogServiceImpl")
    private BlogService blogService;

    @Autowired
    @Qualifier("UserServiceImpl")
    public UserService userService;


    //    增加
    @RequestMapping("/addAppreciate")
    @ResponseBody
    public String addAppreciate(Appreciate appreciate){
        int userId = appreciate.getUserId();
        int blogId = appreciate.getBlogId();
        int uid = appreciate.getUid();//被点赞人
        //所属博客
        Blogs blogs = blogService.queryBlogById(blogId);
        //被点赞人
        Users users = userService.queryUserById(uid);
        String message;
        //找点赞记录
        Appreciate appre = appreciateService.queryAppreciateByUidAndBid(userId,  blogId);
        if(appre==null){
            //        //获取当前时间
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateTime.format(formatter));
            String time=dateTime.format(formatter);
            Timestamp timestamp = Timestamp.valueOf(time);
            appreciate.setCreatetime(timestamp);
            //增加点赞
            appreciateService.addAppreciate(appreciate);
            //博客表的点赞数+1
            blogs.setAppreciate(blogs.getAppreciate()+1);
            blogService.updateBlog(blogs);
            //被点赞用户的点赞数也+1
            users.setAppreciate(users.getAppreciate()+1);
            userService.updateUser(users);
            message = "点赞成功";
            System.out.println(message);
            return message;
        }
        else{
            //取消点赞
            int id = appre.getId();
            appreciateService.deleteAppreciateById(id);
            //博客点赞数-1
            blogs.setAppreciate(blogs.getAppreciate()-1);
            blogService.updateBlog(blogs);
            //用户点赞数-1
            users.setAppreciate(users.getAppreciate()-1);
            userService.updateUser(users);
            message = "已取消点赞";
        }
        return message;

    }









}
