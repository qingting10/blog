package com.controller;

import com.pojo.Attention;
import com.pojo.Users;
import com.service.AttentionService;
import com.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/attention")
public class AttentionController {

    @Autowired
    @Qualifier("AttentionServiceImpl")
    private AttentionService attentionService;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    //增
    @RequestMapping("/addAttention")
    @ResponseBody
    public String addAttention(Attention attention){
        int userId = attention.getUserId();
        int byUserId = attention.getByUserId();
        //查看是否已经存在此条记录
        Attention at = attentionService.queryAttenByUidAndBuid(userId,byUserId);
        if(at != null){
            return "已经关注过，不必再关注";
        }
        else{
            //被关注用户的粉丝+1
            Users users = userService.queryUserById(byUserId);
            users.setFans(users.getFans()+1);
            userService.updateUser(users);

            //获取当前时间
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateTime.format(formatter));
            String time=dateTime.format(formatter);
            Timestamp timestamp = Timestamp.valueOf(time);
            attention.setAttentionTime(timestamp);
            attentionService.addAttention(attention);
            return "成功关注";
        }


    }

    //获取用户列表
    @RequestMapping("/attentionList")
    @ResponseBody
    public String attentionList(int userId){
        List<Users> usersList = new ArrayList<>();
        //获取 “我的关注” 集合，
        List<Attention> atList = attentionService.queryAttentionListByUid(userId);
        Iterator<Attention> it = atList.iterator();
        while (it.hasNext()){
            Attention attention = it.next();
            int byUserId = attention.getByUserId();
            Users users = userService.queryUserById(byUserId);
            usersList.add(users);
        }
        JSONArray jsonArray = new JSONArray(usersList);
        return jsonArray.toString();
    }

    //取消关注
    @RequestMapping("/cancelAttention")
    @ResponseBody
    public String cancelAttention(int userId, int byUserId){
        Attention attention = attentionService.queryAttenByUidAndBuid(userId, byUserId);
        int id = attention.getId();
        attentionService.deleteAttentionById(id);
        //被关注用户的粉丝数-1
        Users users = userService.queryUserById(byUserId);
        users.setFans(users.getFans()-1);
        userService.updateUser(users);
        return null;
    }

    //粉丝列表
    @RequestMapping("/fansList")
    @ResponseBody
    public String fansList(int byUserId){
        //新建用户列表    存储粉丝
        List<Users> list = new ArrayList<>();
        List<Attention> attentionList = attentionService.queryAttentListBybyuid(byUserId);
        //遍历粉丝列表，获取粉丝用户
        Iterator<Attention> it = attentionList.iterator();
        while(it.hasNext()){
            Attention attention = it.next();
            //谁关注我？ 的uid
            int userId = attention.getUserId();
            Users users = userService.queryUserById(userId);
            list.add(users);
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }


}
