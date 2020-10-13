package com.controller;

import com.pojo.Blogs;
import com.pojo.Reply;
import com.pojo.ReplyUserVO;
import com.pojo.Users;
import com.service.BlogService;
import com.service.NoticeService;
import com.service.ReplyService;
import com.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    @Qualifier("ReplyServiceImpl")
    private ReplyService replyService;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("BlogServiceImpl")
    private BlogService blogService;

    @Autowired
    @Qualifier("NoticeServiceImpl")
    private NoticeService noticeService;

//    全部回复
    @RequestMapping("/allReply")
    public String allReply(Model model){
        List<ReplyUserVO> list = replyService.queryAllReply();
        model.addAttribute("list", list);
        return "allReply";
    }

    //所有被举报
    @RequestMapping("/showUnhealth")
    public String showUnhealth(Model model){
        List<ReplyUserVO> list = replyService.statusList();
        model.addAttribute("list", list);
        return "allReply";
    }

    //去评论详情页
    @RequestMapping("/goDetail/{replyId}")
    public String goDetail(@PathVariable("replyId")int id, Model model){
        Reply reply = replyService.queryReplyById(id);
        model.addAttribute("reply", reply);
        return "replydetail";
    }

    //撤销举报
    @RequestMapping("/cancelStatus/{replyId}")
    public String cancelStatus(@PathVariable("replyId") int id){
        replyService.cancelStatus(id);
        return "redirect:/reply/showUnhealth";
    }

//    删除
    @RequestMapping("/del/{replyId}")
    public String deleteReply(@PathVariable("replyId") int id){
        //删除 “评论” 的通知
        noticeService.deleteNoticeByTargetId(id);
        //博客评论数-1
        Reply reply = replyService.queryReplyById(id);
        int blogId = reply.getBlogId();
        Blogs blogs = blogService.queryBlogById(blogId);
        blogs.setCommunicate(blogs.getCommunicate()-1);
        blogService.updateBlog(blogs);
        //用户评论数-1
        int userId = reply.getUserId();
        Users users = userService.queryUserById(userId);
        users.setCommunicate(users.getCommunicate()-1);
        userService.updateUser(users);
        //删除评论
        replyService.deleteReply(id);
        return "redirect:/reply/allReply";
    }

    //    增加
    @RequestMapping("/addReply")
    @ResponseBody
    public String addReply(Reply reply){
                //获取当前时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        String time=dateTime.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(time);
        reply.setReplyTime(timestamp);
        reply.setStatus("正常");
        replyService.addReply(reply);
        //博客的被评论数+1
        int blogId = reply.getBlogId();
        Blogs blogs = blogService.queryBlogById(blogId);
        blogs.setCommunicate(blogs.getCommunicate()+1);
        blogService.updateBlog(blogs);
        //用户的被评论数+1
        int userId = blogs.getUserId();
        Users users = userService.queryUserById(userId);
        users.setCommunicate(users.getCommunicate()+1);
        userService.updateUser(users);
        return null;
    }


//    更改
    @RequestMapping("/updateReply")
    public String updateReply(Reply reply){
        replyService.updateReply(reply);
        return "redirect:/reply/allReply";
    }

    //根据username找评论
    @RequestMapping("/queryReply")
    public String queryReply(String username, Model model){
        List<ReplyUserVO> list = replyService.replyvoListByUsername(username);
        if(list==null){
            list = replyService.queryAllReply();
            model.addAttribute("error","未查到");
        }
        model.addAttribute("list",list);
        return "allReply";
    }

    //根据所有博客id  获取所有博客一级评论
    @RequestMapping("/getFirstFloor")
    @ResponseBody
    public String getFirstFloor(int blogId){
        //最外面的大集合
        List list = new ArrayList();
        //一级评论
        List<ReplyUserVO> onelist = replyService.queryFirstReplyByBid(blogId);
        //遍历一级评论
        Iterator<ReplyUserVO> it = onelist.iterator();
        while (it.hasNext()){
            ReplyUserVO replyUserVO = it.next();
            int parentId = replyUserVO.getId();
            List<ReplyUserVO> twolist = replyService.querySecondReplyByPidAndBid(parentId,blogId);
            
            Map map = new HashMap<>();
            map.put("twolist", twolist);
            map.put("onelist",replyUserVO);
            list.add(map);
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //根据id  删除评论以及它的子评论（parendId==id）
    @RequestMapping("deleteReplyAndChild")
    @ResponseBody
    public String deleteReplyAndChild(int id, int size){
        //子评论
        int parentId = id;
        //删除通知
        noticeService.deleteNoticeByTargetId(id);
        //博客评论数-（size+1）
        Reply reply = replyService.queryReplyById(id);
        int blogId = reply.getBlogId();
        Blogs blogs = blogService.queryBlogById(blogId);
        blogs.setCommunicate(blogs.getCommunicate()-size-1);
        blogService.updateBlog(blogs);
        //这个博客的用户评论数-（size+1）
        int userId = blogs.getUserId();
        Users users = userService.queryUserById(userId);
        users.setCommunicate(users.getCommunicate()-size-1);
        userService.updateUser(users);
        //删除评论
        replyService.deleteReplyByidAndPid(id,parentId);
        System.out.println("==========删除成功===========");
        return null;
    }

    //评论举报
    @RequestMapping("/forbidReply")
    @ResponseBody
    public String forbidReply(int rid){
        replyService.setNormal(rid);
        return null;

    }

}
