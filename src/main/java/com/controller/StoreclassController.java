package com.controller;

import com.pojo.Storeclass;
import com.service.StoreclassService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/storeclass")
public class StoreclassController {
    @Autowired
    @Qualifier("StoreclassServiceImpl")
    private StoreclassService storeclassService;

    //新增
    @RequestMapping("/addStoreclass")
    @ResponseBody
    public String addStoreclass(Storeclass storeclass){
        int uid = storeclass.getUid();
        String classname = storeclass.getClassname();
        Storeclass sc = storeclassService.queryStoreclassByUidAndClassname(uid, classname);
        if(sc != null){
            return "此分类已存在";
        }
        else{
            //获取当前时间
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateTime.format(formatter));
            String time=dateTime.format(formatter);
            Timestamp timestamp = Timestamp.valueOf(time);
            storeclass.setCreatetime(timestamp);
            storeclassService.addStoreclass(storeclass);
            return "分类创建成功";
        }


    }

    //获取所有分类列表
    @RequestMapping("/getList")
    @ResponseBody
    public String getList(int uid){
        List<Storeclass> list = storeclassService.queryStoreListByUid(uid);
        JSONArray jsonArray = new JSONArray(list);
        return jsonArray.toString();
    }

    //获取分类名
    @RequestMapping("/getScId")
    @ResponseBody
    public String getScId(String classname, int uid){
        Storeclass storeclass = storeclassService.queryStoreclassByUidAndClassname(uid, classname);
        Integer cid = storeclass.getId();
        return cid.toString();
    }





}
