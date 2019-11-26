package com.zhs.service.impl;

import com.zhs.dao.SearchRecordRepository;
import com.zhs.entity.SearchRecord;
import com.zhs.service.ISearchRecordService;
import com.zhs.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @project: study-resource
 * @author: zhs
 * @date: 2019/11/24 15:15
 * @package: com.zhs.service.impl
 * @description:
 */

@Service
@Slf4j
public class SearchRecordServiceImpl implements ISearchRecordService {

    @Autowired
    private SearchRecordRepository searchRecordRepository;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    @Override
    public void add(SearchRecord searchRecord) {
                searchRecord.setId(snowflakeIdWorker.nextId());
                searchRecord.setCreateTime(new Date());
        searchRecordRepository.save(searchRecord);
    }
}
