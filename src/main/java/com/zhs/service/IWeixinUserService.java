package com.zhs.service;

import com.zhs.dto.WeixinUserDto;
import com.zhs.entity.WeiXinUser;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 16:40
 * @Description:
 * @version: 1.0
 */
public interface IWeixinUserService {



    void add(WeixinUserDto weixinUserDto);


    void update(WeixinUserDto weixinUserDto);

    WeiXinUser findUserByOpenId(String openId);
}
