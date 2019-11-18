package com.zhs.controller;

import com.zhs.service.ISignRecordsService;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/11/15 23:08
 * @package: com.zhs.controller(
 * @description:
 */

@Api(description = "签到的API接口")
@RestController
@RequestMapping("/v1/api/sign-record")
public class SignRecordsController {


    @Autowired
    private ISignRecordsService signRecordsService;

    @PostMapping
    @ApiOperation(value = "签到",notes = "签到")
    public Result signRecord(String openId){
        signRecordsService.SignRecord(openId);
        return Result.success();
    }

    @GetMapping
    @ApiOperation(value = "判断用户是否今天已经签到")
    public Result isSign(String openId){
        final boolean sign = signRecordsService.isSign(openId);
        return Result.success(sign);
    }
}
