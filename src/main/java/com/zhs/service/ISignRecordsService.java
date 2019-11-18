package com.zhs.service;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/11/15 23:00
 * @package: com.zhs.service
 * @description:
 */
public interface ISignRecordsService {


    void  SignRecord(String openId);

    boolean isSign(String openId);
}
