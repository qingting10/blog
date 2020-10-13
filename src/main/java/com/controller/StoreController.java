package com.controller;

import com.pojo.Blogs;
import com.pojo.Store;
import com.service.BlogService;
import com.service.StoreService;
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
@RequestMapping("/store")
public class StoreController {
    @Autowired
    @Qualifier("StoreServiceImpl")
    private StoreService storeService;

    @Autowired
    @Qualifier("BlogServiceImpl")
    private BlogService blogService;

    //增
    @RequestMapping("/addStore")
    @ResponseBody
    public String addStore(Store store){
        int userId = store.getUserId();
        int blogId = store.getBlogId();
        int scId = store.getScId();
        //检查是否已经收藏过
        Store st = storeService.queryStoreByUidAndBidAndscId(userId,blogId,scId);
        if(st != null){
            return "已经收藏过,不必再收藏";
        }
        else{
            //获取当前时间
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateTime.format(formatter));
            String time=dateTime.format(formatter);
            Timestamp timestamp = Timestamp.valueOf(time);
            store.setStoretime(timestamp);
            storeService.addStore(store);
            return "收藏成功";
        }

    }

    //查找博客列表
    @RequestMapping("blogList")
    @ResponseBody
    public String blogList(int userId , int scId){
        //博客id列表
        List<Integer> bidList = storeService.queryBidByUserIdAndscId(userId, scId);
        //博客列表
        List<Blogs> blogsList = new ArrayList<>();
        //遍历博客id列表
        Iterator<Integer> it = bidList.iterator();
        while (it.hasNext()){
            Blogs blog = blogService.queryBlogById(it.next());
            blogsList.add(blog);
        }
        JSONArray jsonArray = new JSONArray(blogsList);
        return jsonArray.toString();
    }

    //删除收藏
    @RequestMapping("/deleteStore")
    @ResponseBody
    public String deleteStore(Store store){
        int blogId= store.getBlogId();
        int userId = store.getUserId();
        int scId = store.getScId();
        //获取store对象
        Store st = storeService.queryStoreByUidAndBidAndscId(userId,blogId,scId);
        //收藏表id
        int id = st.getId();
        storeService.deleteStoreById(id);
        return null;
    }


}
