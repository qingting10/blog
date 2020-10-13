package com.controller;

import com.pojo.Record;
import com.pojo.Users;
import com.service.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    //controller调用service层
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;    //用户

    @Autowired
    @Qualifier("RecordServiceImpl")
    private RecordService recordService;    //日志表

    @Autowired
    @Qualifier("NoticeServiceImpl")
    private NoticeService noticeService;    //通知表

    @Autowired
    @Qualifier("BlogServiceImpl")
    private BlogService blogService;        //博客表

    @Autowired
    @Qualifier("ReplyServiceImpl")
    private ReplyService replyService;      //评论表

    @Autowired
    @Qualifier("ClassifyServiceImpl")
    private ClassifyService classifyService;    //博客分类表

    @Autowired
    @Qualifier("AttentionServiceImpl")
    private AttentionService attentionService;  //关注表

    @Autowired
    @Qualifier("StoreServiceImpl")
    private StoreService storeService;      //收藏表

    @Autowired
    @Qualifier("StoreclassServiceImpl")
    public StoreclassService storeclassService;     //收藏分类表

    @Autowired
    @Qualifier("AppreciateServiceImpl")
    private AppreciateService appreciateService;    //点赞表


    private Integer flag;   //验证用户

    public Integer getFlag(){
        return flag;
    }

    public void setFlag(Integer flag){
        this.flag = flag;
    }

    //查询全部的用户
    @RequestMapping("/allUser")
    public String list(Model model){
        List<Users> list = userService.queryAllUsers();
        System.out.println(list);
        model.addAttribute("list",list);
        return "allUser";//返回allUser页面
    }

    //去新增用户的界面
    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "addUser";
    }

    //添加用户
    @RequestMapping("/addUser")
    public String addUser(Users users){
        //获取当前时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        String time=dateTime.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(time);
        users.setCreateTime(timestamp);//设置创建时间为当前时间
        users.setRole("管理员");
        userService.addUser(users);
        return "redirect:/user/allUser";
        //添加用户要返回到原来显示全部用户的页面，不能不跳转，所以要重定向
    }

    //删除用户
    @RequestMapping("/del/{userId}")
    public String deleteUser(@PathVariable("userId") int id){
        //删除通知表
        noticeService.deleteNoticeByreceiveUserId(id);
        //删除博客表
        blogService.deleteBlogByUserId(id);
        //删除评论表
        replyService.deleteReplyByUserid(id);
        //删博客分类表
        classifyService.deleteClassifyByUid(id);
        //删关注表
        //删收藏表
        //删收藏分类表
        //删点赞表

        //删除用户
        userService.deleteUserById(id);
        return "redirect:/user/allUser";
    }

    //更新
    @RequestMapping("/updateUser")
    public String updateUser(Model model, Users user){
        userService.updateUser(user);
        Users users = userService.queryUserById(user.getId());
        model.addAttribute("users",users);
        return null;
    }

    //  查询用户名
    @RequestMapping("/queryUser")
    public String queryUser(String username, Model model){
        Users users = userService.queryUserByUsername(username);
        System.out.println(users);
        List<Users> list = new ArrayList<Users>();
        list.add(users);
        if(users==null){
            list = userService.queryAllUsers();
            model.addAttribute("error","未查到");

        }
        model.addAttribute("list",list);
        return "allUser";

    }

    //    验证普通用户登录门户网站
    @RequestMapping("/isLoginUser")
    @ResponseBody
    //输入参数：用户输入的用户名、密码
    public String isLoginUser(String username, String password) {
        //数据库里符合的用户
        Users u = userService.isLoginUser(username);
        if (u == null) {
            return null;
        }
        String upass = u.getPassword();//真正的密码
        System.out.println("sqlpass=" + upass);
        System.out.println(password.equals(upass));
        if (password.equals(upass)) {
            flag = u.getId();   //用户合法的话，就返回用户id
            System.out.println("用户id----"+flag);
            return flag.toString();
        }
        else {
            return null;
        }
    }



//    验证管理员登录
    @RequestMapping("/isLoginManager")
    public String isLoginManager(Users users){
        //待验证的用户名，密码
        String username = users.getUsername();
        String password = users.getPassword();
        System.out.println("username=" + users.getUsername());
        System.out.println("password=" + users.getPassword());
        //数据库里符合的用户
        Users u =  userService.isLoginUser(users.getUsername());
//        验证是不是管理员
        String role = u.getRole();
        System.out.println(role);
        if(role.equals("管理员")){
            String upass = u.getPassword();//真正的密码
            System.out.println("sqlpass=" + upass);
            System.out.println(password.equals(upass));
            if(password.equals(upass.trim())){
                //记录到日志表
                //获取当前时间
                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println(dateTime.format(formatter));
                String time=dateTime.format(formatter);
                Timestamp timestamp = Timestamp.valueOf(time);
                recordService.addRecord();//添加一条记录
                //找到上面的这条数据进行修改
                Record record = recordService.queryRecordLast();
                record.setManagerId(u.getId());
                record.setCreatetime(timestamp);
                System.out.println(record);
                recordService.updateRecord(record);

                return "redirect:/user/allUser";

            }
            else {
                return "error";
            }
        }
        else{
            return "error";
        }

    }

    //根据博客id 获取用户
    @RequestMapping("queryUserByBlogid")
    @ResponseBody
    public String queryUserByBlogid(int id){
//        int id = users.getId();
        System.out.println("bid------------------"+id);
        Users user = userService.queryUserByBlogid(id);
        JSONObject jsonObject = JSONObject.fromObject(user);
        return jsonObject.toString();
    }

    //修改头像
    @RequestMapping("/updateAva")
    @ResponseBody
    public String updateAva(Users users){
        int id = users.getId();
        String ava = users.getAvatar();
        Users user = userService.queryUserById(id);
        user.setAvatar(ava);
        userService.updateUser(user);
        return null;
    }

    //查用户   根据id
    @RequestMapping("/getUserById")
    @ResponseBody
    public String getUserById(Users users){
        int id = users.getId();
        Users user = userService.queryUserById(id);
        JSONObject jsonObject = JSONObject.fromObject(user);
        return jsonObject.toString();
    }

    //输入用户名，检测是否用户名已存在
    @RequestMapping("/checkUsername")
    @ResponseBody
    public String checkUsername(String username){
        Users users = userService.queryUserByUsername(username);
        String message;
        if(users == null){
            message = "null";
        }
        else{
            System.out.println(users.getRole());
            if(users.getRole().equals("用户")){
                message = "用户名已存在";
//                return message;
            }
            else{
                message = "null";
            }
        }
        return message;
    }

    //用户注册
    @RequestMapping("/addVueUser")
    @ResponseBody
    public String addVueUser(Users users){
        //获取当前时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        String time=dateTime.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(time);
        //设置属性
        users.setCreateTime(timestamp);
        users.setAvatar("/img/17.a9ed5719.jpg");
        users.setRole("用户");
        userService.addUser(users);
        return null;
    }

    //找到博客数最多的10个人
    @RequestMapping("blognumUp")
    @ResponseBody
    public String blognumUp(){
        List<Users> list = userService.queryUsersMoreBlog();
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //排名计算
    @RequestMapping("/computeFloor")
    @ResponseBody
    public String computeFloor(int uid){
        Users users = userService.queryUserById(uid);
        int blognum = users.getBlognum();           //博客数

        Map<String,Integer> map = new HashMap<>();
        //等级        每25篇加一个等级，等级不限
        Integer floor = blognum/25+1;
        map.put("floor", floor);
        //博客排名
        Integer blogFloor = 0;
        List<Users> byBlog = userService.orderUser("blognum");
        for(Users user: byBlog){
            blogFloor = blogFloor + 1;
            if(user.getId()==uid){

                map.put("blogFloor", blogFloor);
            }
            else{
                continue;
            }
        }
        // 粉丝排名：3
        Integer fanFloor = 0;
        List<Users> byFan = userService.orderUser("fans");
        for(Users user : byFan){
            fanFloor = fanFloor +1;
            if(user.getId() == uid){

                map.put("fanFloor", fanFloor);
            }
            else{
                continue;
            }
        }
        // 点赞排名：3
        Integer wellFloor = 0;
        List<Users> byWell = userService.orderUser("appreciate");
        for(Users user : byWell){
            wellFloor = wellFloor +1;
            if(user.getId() == uid){

                map.put("wellFloor", wellFloor);
            }
            else{
                continue;
            }
        }
        // 评论排名：3
        Integer replyFloor = 0;
        List<Users> byCom = userService.orderUser("communicate");
        for(Users user : byCom){
            replyFloor = replyFloor +1;
            if(user.getId() == uid){

                map.put("replyFloor", replyFloor);
            }
            else{
                continue;
            }
        }
        // 访问排名：3
        Integer visitFloor = 0;
        List<Users> byVisit = userService.orderUser("visitedNumber");
        for(Users user : byVisit){
            visitFloor = visitFloor +1;
            if(user.getId() == uid){

                map.put("visitFloor", visitFloor);
            }
            else{
                continue;
            }
        }
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();
    }


}
