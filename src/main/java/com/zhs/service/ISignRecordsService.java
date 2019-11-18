package com.zhs.service;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/11/15 23:00
 * @package: com.zhs.service
 * @description:
 */
public interface ISignRecordsService {


    /**
     * 签到
     * @param openId
     */
    void  SignRecord(String openId);


    /**
     * 判断是否签到
     * @param openId
     * @return
     */
    boolean isSign(String openId);
}
