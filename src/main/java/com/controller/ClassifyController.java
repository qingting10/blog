package com.controller;

import com.mysql.cj.xdevapi.JsonArray;
import com.pojo.Classify;
import com.service.BlogService;
import com.service.ClassifyService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/classify")
public class ClassifyController {
    @Autowired
    @Qualifier("ClassifyServiceImpl")
    private ClassifyService classifyService;

    @Autowired
    @Qualifier("BlogServiceImpl")
    private BlogService blogService;

    private Integer integer;

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    //查所有
    @RequestMapping("/allClassify")
    @ResponseBody
    public String allClassify(int uid){
        List<String> list = classifyService.queryAllClassifyByUid(uid);
        JSONArray jsonArray = new JSONArray(list);
//
        System.out.println(jsonArray.toString());
        return jsonArray.toString();
    }

    //新增
    @RequestMapping("addClassify")
    @ResponseBody
    public String addClassify(Classify classify){
        //获取当前时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        String time=dateTime.format(formatter);
        Timestamp timestamp = Timestamp.valueOf(time);
        classify.setCreatetime(timestamp);//设置创建时间为当前时间
        classifyService.addClassify(classify);
        return null;
    }

    //查询classname对应的id
    @RequestMapping("/getCid")
    @ResponseBody
    public String getCid(@RequestParam("classname") String classname, @RequestParam("uid") int uid){
//        String classname = classify.getClassname();
//        int uid = classify.getUid();
        System.out.println(classname);
        System.out.println("uid"+uid);
        Classify classify1 = classifyService.queryClassifyByClassname(classname,uid);
        Integer id = classify1.getId();
        System.out.println(id.toString());
        return id.toString();

    }

    //先找到userId对应的classname列表
    //再根据获取cid，再获取count(cid)
    @RequestMapping("/classnameList")
    @ResponseBody
    public String classnameList(int userId){
        //存储输出
        List list = new ArrayList();

        //找到userId对应的classname列表
        List<Classify> classifyList = classifyService.queryListByUid(userId);
        System.out.println("==================");
        System.out.println(classifyList);
        Iterator<Classify> it = classifyList.iterator();
        while(it.hasNext()){
            //再根据获取cid，再获取count(cid)
            Classify classify = it.next();
            System.out.println("==================");
            System.out.println(classify);
            int cid = classify.getId();
            Integer num = blogService.countCid(cid);
            //存储map
            Map map = new HashMap();
            map.put("classify", classify);
            map.put("num", num);
            list.add(map);
        }
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }



}
