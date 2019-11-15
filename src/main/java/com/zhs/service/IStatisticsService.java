package com.zhs.service;

import com.zhs.vo.StatisticsVo;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/14 16:05
 * @Description: 获取各种统计的数据
 * @version: 1.0
 */
public interface IStatisticsService {


    /**
     * 获取个人下载数 提交数以及 当前积分
     * @param openId
     * @return
     */
    StatisticsVo getPersonnal(String openId);
}
