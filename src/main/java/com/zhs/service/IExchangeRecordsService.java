package com.zhs.service;

import com.zhs.entity.ExchangeRecords;
import com.zhs.vo.ExchangeRecordsVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 17:15
 * @Description:
 * @version: 1.0
 */
public interface IExchangeRecordsService {

    String add(ExchangeRecords exchangeRecords);


    Page<ExchangeRecordsVo> getExchangerRecords(String openId, int page, int pageSize);
}
