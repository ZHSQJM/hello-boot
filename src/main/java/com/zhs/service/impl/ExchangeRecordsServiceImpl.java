package com.zhs.service.impl;

import com.zhs.dao.ExchangeRecordsRepository;
import com.zhs.dao.WeixinUserReposotory;
import com.zhs.entity.ExchangeRecords;
import com.zhs.entity.WeiXinUser;
import com.zhs.exception.ZhsException;
import com.zhs.service.IExchangeRecordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 17:15
 * @Description: 兑换记录的
 * @version: 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ExchangeRecordsServiceImpl implements IExchangeRecordsService {

    @Autowired
    private ExchangeRecordsRepository exchangeRecordsRepository;

    @Autowired
    private WeixinUserReposotory weixinUserReposotory;

    @Override
    public void add(ExchangeRecords exchangeRecords) {
        Optional<WeiXinUser> optional = weixinUserReposotory.findById(exchangeRecords.getUserId());
        if(!optional.isPresent()){
            throw new ZhsException("没有该用户");
        }
        WeiXinUser weixinUser = optional.get();
        Integer needIntegral = exchangeRecords.getIntegral();
        Integer hasIntegral = weixinUser.getIntegral();
        if(hasIntegral<needIntegral){
            throw  new ZhsException("积分不足,请及时登录领取积分");
        }
        Integer nowIntegral =  hasIntegral - needIntegral;
        weixinUser.setIntegral(nowIntegral);
        weixinUserReposotory.save(weixinUser);
        exchangeRecords.setStatus(0);
        exchangeRecordsRepository.save(exchangeRecords);
    }
}