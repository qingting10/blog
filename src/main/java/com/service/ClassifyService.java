package com.service;

import com.pojo.Classify;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassifyService {

    //增加一个分类名
    int addClassify(Classify classify);

    //删除
    int deleteClassifyById(int id);

    //修改
    int updateClassify(Classify classify);

    //根据id查
    Classify queryClassifyById(int id);

    //查找所有classname
    List<String> queryAllClassifyByUid(int uid);

    //根据uid查找所有classify
    List<Classify> queryListByUid(@Param("uid") int uid);

    //根据classname查询classify
    Classify queryClassifyByClassname(String classname, int uid);

    //    删表  根据uid
    int deleteClassifyByUid(int uid);
}
