package com.controller;

import com.pojo.*;
import com.service.BlogService;
import com.service.NoticeService;
import com.service.RecordService;
import com.service.UserService;
import lombok.NoArgsConstructor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    @Qualifier("NoticeServiceImpl")     //要在spring-service.xml里面注册bean,不然这个qulifier会报错
    private NoticeService noticeService;
    @Autowired
    @Qualifier("RecordServiceImpl")
    private RecordService recordService;
    @Autowired
    @Qualifier("BlogServiceImpl")
    private BlogService blogService;
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

//    查询全部
    @RequestMapping("/allNotice")
    public String list(Model model){
        List<Notices> list = noticeService.queryAllNotice();
        model.addAttribute("list", list);
        return "/allNotice";
    }

//    去新增通知页面
    @RequestMapping("/toAddNotice")
    public  String toAddNotice(){
        return "/addNotice";
    }

//    增加通知
    @RequestMapping("/addNotice")
    public String addNotice(Notices notices){
//      当前管理员的id
        Record record = recordService.queryRecordLast();
        notices.setSendUserId(record.getManagerId());
        notices.setType("公告");
//        获取发送时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        String time=dateTime.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(time);
        notices.setSendTime(timestamp);
        notices.setStatus("未读");
        noticeService.addNotice(notices);
        return "redirect:/notice/allNotice";
    }

//    删除通知
    @RequestMapping("/del/{noticeId}")
    public String deleteNotice(@PathVariable("noticeId") int id){
        noticeService.deleteNotice(id);
        return "redirect:/notice/allNotice";
    }

    //添加关注记录通知
    @RequestMapping("/attentionRecord")
    @ResponseBody
    public String attentionRecord(Notices notices){
        notices.setStatus("未读");
        //        获取发送时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        String time=dateTime.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(time);
        notices.setSendTime(timestamp);
        noticeService.addNotice(notices);
        return "记录";
    }

    //查找我的消息
    @RequestMapping("/messagelist")
    @ResponseBody
    public String messagelist(int receiveUserId){
//        当前管理员的id
        Record record = recordService.queryRecordLast();
        int managId = record.getManagerId();
        System.out.println("==========管理员==========");
        System.out.println(managId);
        List<Blogs> pubList = blogService.queryBlogsByUserId(managId);
        Integer pubLength = pubList.size();
        Users pubusers = userService.queryUserById(managId);
        //找关注
        List<Notices> attenList = noticeService.noticeList(receiveUserId, "关注");
        Integer attenLength = attenList.size();
        //找评论
        List<Notices> comList = noticeService.noticeList(receiveUserId, "评论");
        Integer comLength = comList.size();
        //找点赞
        List<Notices> appreList = noticeService.noticeList(receiveUserId, "点赞");
        Integer appreLength = appreList.size();
        //存到map
        Map map = new HashMap();
        map.put("pubList", pubList);        //公告集合
        map.put("pubLength", pubLength);    //集合长度
        map.put("pubusers", pubusers);      //管理员用户
        map.put("attenList", attenList);    //关注
        map.put("attenLength", attenLength);
        map.put("comList", comList);        //评论
        map.put("comLength", comLength);
        map.put("appreList", appreList);    //点赞
        map.put("appreLength", appreLength);
        Integer allLength = pubLength + attenLength + comLength + appreLength;    //总的未读长度
        map.put("allLength", allLength);
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();
    }

    //找关注集合（包username）
    @RequestMapping("/attenAndUnameList")
    @ResponseBody
    public String attenAndUnameList(int receiveUserId){
        List<NoticeUserBlogVO> list = noticeService.noticeUnameList(receiveUserId);
        //遍历所有list,把status都改为"已读"
        for(NoticeUserBlogVO noticeVO : list){
            Notices notice = noticeService.queryNoticeById(noticeVO.getId());
            notice.setStatus("已读");
            noticeService.updateNotice(notice);
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //找点赞/评论集合（包括usname, title）
    @RequestMapping("/titleUnameNoticeList")
    @ResponseBody
    public String titleUnameNoticeList(int receiveUserId, String type){
        List<NoticeUserBlogVO> list = noticeService.noticeUnameTitleList(receiveUserId, type);
        //遍历所有list,把status都改为"已读"
        for(NoticeUserBlogVO noticeVO : list){
            Notices notice = noticeService.queryNoticeById(noticeVO.getId());
            notice.setStatus("已读");
            noticeService.updateNotice(notice);
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //删除记录
    @RequestMapping("/deleteUserNotice")
    @ResponseBody
    public String deleteUserNotice(int id){
        noticeService.deleteNotice(id);
        return null;
    }

}


