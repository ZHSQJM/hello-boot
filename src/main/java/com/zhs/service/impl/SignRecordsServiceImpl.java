package com.zhs.service.impl;

import com.zhs.dao.SignRecordsRepository;
import com.zhs.dao.WeixinUserReposotory;
import com.zhs.entity.SignRecords;
import com.zhs.entity.WeiXinUser;
import com.zhs.enums.ResultEnum;
import com.zhs.exception.ZhsException;
import com.zhs.service.ISignRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/11/15 23:02
 * @package: com.zhs.service.impl
 * @description:
 */
@Service
public class SignRecordsServiceImpl implements ISignRecordsService {

    @Autowired
    private SignRecordsRepository signRecordsRepository;

    @Autowired
    private WeixinUserReposotory weixinUserReposotory;
    @Override
    public void SignRecord(String openId) {

        String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
        final SignRecords byDateAndOpenid = signRecordsRepository.findByDateTimeAndOpenid(strNow, openId);
          if(byDateAndOpenid!=null){
              throw  new ZhsException(ResultEnum.USER_HAS_SOGN);
          }

          SignRecords signRecords  = new SignRecords();
          signRecords.setDate(new Date()).setDateTime(strNow).setOpenid(openId);

          signRecordsRepository.save(signRecords);

        final WeiXinUser byOpenId = weixinUserReposotory.findByOpenId(openId);


        byOpenId.setIntegral(byOpenId.getIntegral()+10);

        weixinUserReposotory.save(byOpenId);

    }

    @Override
    public boolean isSign(String openId) {
        String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();

        final SignRecords byDateAndOpenid = signRecordsRepository.findByDateTimeAndOpenid(strNow, openId);

        return byDateAndOpenid ==null?false:true;
    }
}
