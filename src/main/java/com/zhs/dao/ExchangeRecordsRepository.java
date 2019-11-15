package com.zhs.dao;

import com.zhs.entity.ExchangeRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 17:12
 * @Description: 兑换记录的dao
 * @version: 1.0
 */

@Repository
public interface ExchangeRecordsRepository   extends JpaRepository<ExchangeRecords,Long>, JpaSpecificationExecutor<ExchangeRecords> {


    ExchangeRecords findByUserIdAndResourceId(String openId,Long resourceId);


    List<ExchangeRecords> findAllByUserId(String openId);



}
