package com.service;

import com.pojo.Record;

import java.sql.Timestamp;
import java.util.List;

public interface RecordService {
    //增加一个记录
    int addRecord();

    //删除一个记录
    int deleteRecordById( int id);

    //更新记录
    int updateRecord(Record record);

    //查询一个记录
    Record queryRecordById( int id);

    //查询所有记录
    List<Record> queryAllRecord();

    //    找出最后插入的记录
    Record queryRecordLast();
}
