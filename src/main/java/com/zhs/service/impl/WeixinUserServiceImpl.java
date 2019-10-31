package com.zhs.service.impl;

import com.zhs.dao.WeixinUserReposotory;
import com.zhs.dto.WeixinUserDto;
import com.zhs.entity.WeiXinUser;
import com.zhs.service.IWeixinUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 16:40
 * @Description:
 * @version: 1.0
 */

@Service
@Slf4j
public class WeixinUserServiceImpl implements IWeixinUserService {


    @Autowired
    private WeixinUserReposotory weixinUserReposotory;

    @Override
    public void add(WeixinUserDto weixinUserDto) {
        WeiXinUser weixinUser = new WeiXinUser();
        BeanUtils.copyProperties(weixinUserDto,weixinUser);
        weixinUser.setCreateTime(new Date());
        weixinUser.setRecentlyTime(new Date());
        weixinUser.setIntegral(100);
        weixinUserReposotory.save(weixinUser);
    }

    @Override
    public void update(WeixinUserDto weixinUserDto) {
        WeiXinUser weixinUser = new WeiXinUser();
        BeanUtils.copyProperties(weixinUserDto,weixinUser);
        weixinUser.setRecentlyTime(new Date());
        weixinUserReposotory.save(weixinUser);

    }

    @Override
    public WeiXinUser findUserByOpenId(String openId) {
        Optional<WeiXinUser> optionalWeiXinUser = weixinUserReposotory.findById(openId);
        return optionalWeiXinUser.isPresent()?optionalWeiXinUser.get():null;
    }


}
