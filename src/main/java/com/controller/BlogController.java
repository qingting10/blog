package com.controller;

import com.pojo.Blogs;
import com.pojo.BlogsVO;
import com.pojo.Users;
import com.service.AppreciateService;
import com.service.BlogService;
import com.service.NoticeService;
import com.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    @Qualifier("BlogServiceImpl")
    private BlogService blogService;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("NoticeServiceImpl")
    private NoticeService noticeService;

//    增加blog
    @RequestMapping("/addBlog")
    @ResponseBody
    public String addBlog(Blogs blogs){
        int userId = blogs.getUserId();
        Users users = userService.queryUserById(userId);
        users.setBlognum(users.getBlognum()+1); //用户的博客数加一
        userService.updateUser(users);  //并更新users

        //获取当前时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time=dateTime.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(time);
        blogs.setUpdateTime(timestamp);
        blogs.setAppreciate(0);
        blogs.setCommunicate(0);
        blogs.setVisitedNumber(0);
        blogs.setStatus("正常");
        blogService.addBlog(blogs);
        return null;
    }

//    修改blog
    @RequestMapping("updateBlog")
    @ResponseBody
    public String updateBlog(Blogs blog){
        int id = blog.getId();
        String title = blog.getTitle();
        String content = blog.getContent();
        int cid = blog.getCid();
        Blogs blogs = blogService.queryBlogById(id);
        blogs.setTitle(title);
        blogs.setContent(content);
        blogs.setCid(cid);
        blogService.updateBlog(blogs);
        return null;
    }

//    查询所有博客
    @RequestMapping("/allBlog")
    public String list(Model model){
        List<BlogsVO> list = blogService.queryAllBlog();
        model.addAttribute("list", list);
        return "allBlog";
    }

    //查询所有被举报博客
    @RequestMapping("/showUnhealth")
    public String showUnhealth(Model model){
        List<Blogs> list = blogService.statusList();
        model.addAttribute("list",list);
        return "allBlog";
    }

    //到博客详情页
    @RequestMapping("/goBlogDetail/{blogId}")
    public String goBlogDetail(@PathVariable("blogId") int id, Model model){
        Blogs blogs = blogService.queryBlogById(id);
        model.addAttribute("blog", blogs);
        return "blogdetail";
    }

    //撤销举报
    @RequestMapping("/cancelStatus/{blogId}")
    public String cancelStatus(@PathVariable("blogId")int id){
        blogService.cancelStatus(id);
        //重定向到所有博客页
        return "redirect:/blog/showUnhealth";
    }

//    删除博客
    @RequestMapping("/del/{blogId}")
    public String deleteBlog(@PathVariable("blogId") int id){
        //找uid
        Blogs blogs = blogService.queryBlogById(id);
        int userId = blogs.getUserId();
        //删除对应的通知表记录
        noticeService.deleteNoticeByTargetId(id);
        //删除博客
        blogService.deleteBlogById(id);
        //用户的博客数量-1
        Users users = userService.queryUserById(userId);
        users.setBlognum(users.getBlognum() - 1);
        userService.updateUser(users);
        return "redirect:/blog/allBlog";
    }

    //    根据博客标题,查询博客
    @RequestMapping("/queryBlog")
    public String queryBlog(String title, Model model){
        List<BlogsVO> list = blogService.queryBlogByTitleLike(title);
//        System.out.println(blogs);
//        List<BlogsVO> list = new ArrayList<BlogsVO>();
//        list.add(blogs);
        if(list==null){
            //如果查询为空，返回全部的博客，并告诉用户“没查到”
            list = blogService.queryAllBlog();
            model.addAttribute("error","未查到");

        }
        model.addAttribute("list",list);
        return "allBlog";
    }

    //查所有博客返回给vue
    @RequestMapping("/getBlogs")
    @ResponseBody
    public String getBlogs(){
        List<BlogsVO> list = blogService.queryAllBlog();
        JSONArray jsonArray = new JSONArray(list);
//        System.out.println("所有博客--------"+jsonArray.toString());
        return jsonArray.toString();
    }

    //根据bid查blog和username
    @RequestMapping("queryBlogById")
    @ResponseBody
    public String queryBlogById(@RequestParam("id") int id ){
//        System.out.println("id----------------------"+id);
//        int id = blog.getId();
        if(id != 0){
            BlogsVO blogs = blogService.queryUnameAndBlogByBid(id);
            JSONObject jsonObject = JSONObject.fromObject(blogs);
            return jsonObject.toString();
        }
        return "id为空";
    }

    //根据bid查blog和username
    @RequestMapping("queryBlogVo")
    @ResponseBody
    public String queryBlogVo(@RequestParam("id") int id ){
//        System.out.println("id----------------------"+id);
//        int id = blog.getId();
        if(id != 0){
            BlogsVO blogs = blogService.queryUnameAndBlogByBid(id);
//            String strblog = String.valueOf(blogs);
//            System.out.println(strblog);
            JSONObject jsonObject = JSONObject.fromObject(blogs);
            return jsonObject.toString();
        }
        return "id为空";
    }

    //访问量+1
    @RequestMapping("/addVisit")
    @ResponseBody
    public String addVisit(Blogs blog){
        int id = blog.getId();
        Blogs blogs = blogService.queryBlogById(id);
        blogs.setVisitedNumber(blogs.getVisitedNumber()+1);
        blogService.updateBlog(blogs);
        //用户的访问量也要同步
        int userId = blogs.getUserId();
        Users users = userService.queryUserById(userId);
        users.setVisitedNumber(users.getVisitedNumber()+1);
        userService.updateUser(users);
        return null;
    }

    //博客中心排序    --
    @RequestMapping("getBlogList")
    @ResponseBody
    public String getBlogList(int userId){
//        System.out.println("==============");
        List<Blogs> list = blogService.queryBlogsByUserId(userId);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //模糊查询博客
    @RequestMapping("/likeTitleList")
    @ResponseBody
    public String likeTitleList(String title){
        List<BlogsVO> list = blogService.queryBlogByTitleLike(title);
        if(list.size()>0){
            JSONArray jsonArray = new JSONArray(list);
            return jsonArray.toString();
        }
        else{
            return null;
        }

    }

    //举报博客
    @RequestMapping("/forbidBlog")
    @ResponseBody
    public String forbidBlog(int id){
        blogService.putStatus(id);
        return null;

    }

    //删除博客
    @RequestMapping("/deleteVueBlog")
    @ResponseBody
    public String deleteVueBlog(int bid, int uid){
        //博客表里要删除
        blogService.deleteBlogById(bid);
        //删除 评论表中targetId=bid
        noticeService.deleteNoticeByTargetId(bid);
        //用户表的博客数-1
        Users users = userService.queryUserById(uid);
        users.setBlognum(users.getBlognum()-1);
        userService.updateUser(users);
        return null;

    }

    //时间倒叙排序用户的博客
    @RequestMapping("/timeLastBloglist")
    @ResponseBody
    public String timeLastBloglist(int userId){
        List<Blogs> list = blogService.queryListByTimelast(userId);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //访问量降序 博客列表
    @RequestMapping("/orderByVisit")
    @ResponseBody
    public String orderByVisit(int userId){
        List<Blogs> list = blogService.queryListByVisitnum(userId);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //找出最新的6篇博客
    @RequestMapping("sixNewList")
    @ResponseBody
    public String sixNewList(int userId){
        System.out.println("===================");
        System.out.println(userId);
        List<Blogs> list = new ArrayList<>();
        //找出全部
        List<Blogs> blogsList = blogService.queryListByTimelast(userId);
        //如果超过6个就只取6个
        if(blogsList.size()>6){
            for(int i=0; i<6; i++){
                list.add(blogsList.get(i));
            }
        }
        else{
            for(int i=0; i<blogsList.size(); i++){
                list.add(blogsList.get(i));
            }
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //取阅读数最多的blog
    @RequestMapping("/sixHotBlogs")
    @ResponseBody
    public String sixHotBlogs(int userId){
        List<Blogs> blogsList = blogService.hotBlogList(userId);
        //存 <= 6个
        List<Blogs> list = new ArrayList<>();
        if(blogsList.size()<6){
            for(int i=0; i<blogsList.size(); i++){
                list.add(blogsList.get(i));
            }
        }
        else{
            for(int i=0; i<6; i++){
                list.add(blogsList.get(i));
            }
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

//    根据cid uid查博客列表
    @RequestMapping("/cnameBlogList")
    @ResponseBody
    public String cnameBlogList(int cid, int userId){
        List<BlogsVO> list = blogService.blogCnameListByCidAndUid(cid, userId);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

}
