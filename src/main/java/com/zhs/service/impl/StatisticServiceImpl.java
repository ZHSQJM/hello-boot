package com.zhs.service.impl;

import com.zhs.dao.ExchangeRecordsRepository;
import com.zhs.dao.ResourceRepository;
import com.zhs.dao.WeixinUserReposotory;
import com.zhs.entity.ExchangeRecords;
import com.zhs.entity.Resource;
import com.zhs.entity.WeiXinUser;
import com.zhs.service.IStatisticsService;
import com.zhs.vo.StatisticsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/14 16:06
 * @Description:
 * @version: 1.0
 */
@Service
@Slf4j
public class StatisticServiceImpl implements IStatisticsService {

    @Autowired
    private WeixinUserReposotory weixinUserReposotory;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ExchangeRecordsRepository exchangeRecordsRepository;


    @Override
    public StatisticsVo getPersonnal(String openId) {





        StatisticsVo statisticsVo = new StatisticsVo();
        WeiXinUser byOpenId = weixinUserReposotory.findByOpenId(openId);
        Integer integral = byOpenId.getIntegral();
        List<Resource> resourcesByOpenIdAndStatus = resourceRepository.findResourcesByOpenIdAndStatus(openId, 0);
        Integer resourceId = resourcesByOpenIdAndStatus.size();
        List<ExchangeRecords> allByUserId = exchangeRecordsRepository.findAllByUserId(openId);
        Integer download = allByUserId.size();
        statisticsVo.setDownload(download).setIntegral(integral).setResourceId(resourceId);
        return statisticsVo;
    }
}
