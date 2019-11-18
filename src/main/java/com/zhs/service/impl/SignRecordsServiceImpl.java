package com.zhs.service.impl;

import com.zhs.dao.SignRecordsRepository;
import com.zhs.entity.SignRecords;
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
    @Override
    public void SignRecord(String openId) {

        String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
        final SignRecords byDateAndOpenid = signRecordsRepository.findByDateAndOpenid(strNow, openId);

          if(byDateAndOpenid!=null){
              throw  new ZhsException(ResultEnum.USER_HAS_SOGN);
          }

          SignRecords signRecords  = new SignRecords();
          signRecords.setDate(new Date()).setDateTime(strNow).setOpenid(openId);

          signRecordsRepository.save(signRecords);
    }

    @Override
    public
    boolean isSign(String openId) {
        String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();

        final SignRecords byDateAndOpenid = signRecordsRepository.findByDateAndOpenid(strNow, openId);

        return byDateAndOpenid ==null?false:true;
    }
}
