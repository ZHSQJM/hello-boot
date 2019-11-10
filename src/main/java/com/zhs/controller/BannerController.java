package com.zhs.controller;

import com.zhs.dto.BannerDto;
import com.zhs.dto.CategoryDto;
import com.zhs.enums.ResultCode;
import com.zhs.service.IBannerSerivce;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/10/30 16:20
 * @package: com.zhs.controller
 * @description:
 */
@RestController
@Slf4j
@Api(description = "banner图的API接口")
@RequestMapping("/v1/api/banner")
public class BannerController {


    @Autowired
    private IBannerSerivce bannerSerivce;

    @PostMapping
    @ApiOperation(value = "新增banner",notes = "新增banner")
    public Result add(@RequestBody @Valid BannerDto bannerDto){
        bannerSerivce.add(bannerDto);
        return Result.success();
    }

    @GetMapping("/find")
    @ApiOperation(value = "获取banner",notes = "获取banner")
    public Result find(){
        return Result.success(ResultCode.SUCCESS,bannerSerivce.getBanner(0));
    }
}
