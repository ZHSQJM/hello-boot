package com.zhs.controller;

import com.zhs.bo.SessionUser;
import com.zhs.dto.WeixinUserDto;
import com.zhs.entity.WeiXinUser;
import com.zhs.service.IWeixinUserService;
import com.zhs.utils.HttpClientUtil;
import com.zhs.utils.JsonUtils;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 17:22
 * @Description:
 * @version: 1.0
 */

@Api(description = "微信用户的API接口")
@RestController
@CrossOrigin
@RequestMapping("/v1/api/wei-xin")
@Slf4j
public class WeiXinController {

    @Autowired
    private IWeixinUserService weixinUserService;



    @PostMapping("wxLogin")
    @ApiOperation(value = "根据code获取openid",notes = "根据code获取openid")
    public Result wxLogin(WeixinUserDto weixinUserDto){

        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code


        log.info("code"+weixinUserDto.getCode());
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param = new HashMap<>(10);
        param.put("appid","wxd1d22bfc46b95204");
        param.put("secret","6e5a5175e3b76ecb7bfc42270955a946");
        param.put("js_code",weixinUserDto.getCode());
        param.put("grant_type","authorization_code");
        String wxResult = HttpClientUtil.doGet(url, param);

        SessionUser sessionUser = JsonUtils.jsonToPojo(wxResult, SessionUser.class);

        WeiXinUser userByOpenId = weixinUserService.findUserByOpenId(sessionUser.getOpenid());

        if(userByOpenId!=null){
            weixinUserService.update(weixinUserDto);
        }else {
            weixinUserDto.setOpenId(sessionUser.getOpenid());
            weixinUserService.add(weixinUserDto);
        }
        log.info("wxResult"+wxResult);
        return Result.success();
    }

}
