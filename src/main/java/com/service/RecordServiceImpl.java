package com.service;

import com.dao.RecordMapper;
import com.pojo.Record;

import java.sql.Timestamp;
import java.util.List;

public class RecordServiceImpl implements RecordService {
    private RecordMapper recordMapper;

    public void setRecordMapper(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Override
    public int addRecord() {
        return recordMapper.addRecord();
    }

    @Override
    public int deleteRecordById(int id) {
        return recordMapper.deleteRecordById(id);
    }

    @Override
    public int updateRecord(Record record) {
        return recordMapper.updateRecord(record);
    }

    @Override
    public Record queryRecordById(int id) {
        return recordMapper.queryRecordById(id);
    }

    @Override
    public List<Record> queryAllRecord() {
        return recordMapper.queryAllRecord();
    }

    @Override
    public Record queryRecordLast() {
        return recordMapper.queryRecordLast();
    }
}
