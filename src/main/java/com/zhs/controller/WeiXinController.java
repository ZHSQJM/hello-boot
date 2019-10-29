package com.zhs.controller;

import com.zhs.dto.WeixinUserDto;
import com.zhs.service.IWeixinUserService;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Result wxLogin(String code){

       log.info("code"+code);
        return Result.success();
    }
    @PostMapping
    @ApiOperation(value = "新增微信用户",notes = "新增微信用户")
    public Result add(@RequestBody @Valid WeixinUserDto weixinUserDto){
        weixinUserService.add(weixinUserDto);
        return Result.success();
    }
}
