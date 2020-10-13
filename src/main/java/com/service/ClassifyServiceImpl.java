package com.service;

import com.dao.ClassifyMapper;
import com.pojo.Classify;

import java.util.List;

public class ClassifyServiceImpl implements ClassifyService{
    //调用dao层的操作，设置一个set接口，方便spring管理
    private ClassifyMapper classifyMapper;

    public void setClassifyMapper(ClassifyMapper classifyMapper) {
        this.classifyMapper = classifyMapper;
    }

    @Override
    public int addClassify(Classify classify) {
        return classifyMapper.addClassify(classify);
    }

    @Override
    public int deleteClassifyById(int id) {
        return classifyMapper.deleteClassifyById(id);
    }

    @Override
    public int updateClassify(Classify classify) {
        return classifyMapper.updateClassify(classify);
    }

    @Override
    public Classify queryClassifyById(int id) {
        return classifyMapper.queryClassifyById(id);
    }

    @Override
    public List<String> queryAllClassifyByUid(int uid) {
        return classifyMapper.queryAllClassifyByUid(uid);
    }

    @Override
    public List<Classify> queryListByUid(int uid) {
        return classifyMapper.queryListByUid(uid);
    }

    @Override
    public Classify queryClassifyByClassname(String classname, int uid) {
        return classifyMapper.queryClassifyByClassname(classname, uid);
    }

    @Override
    public int deleteClassifyByUid(int uid) {
        return classifyMapper.deleteClassifyByUid(uid);
    }
}
