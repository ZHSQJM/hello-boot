package com.zhs.controller;

import com.zhs.dto.CategoryDto;
import com.zhs.dto.ResourceDto;
import com.zhs.service.IResourceService;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 11:07
 * @Description:
 * @version: 1.0
 */
@RestController
@Slf4j
@Api(description = "资源的API接口")
@RequestMapping("/v1/api/resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;


    @PostMapping
    @ApiOperation(value = "新增资源",notes = "新增资源")
    public Result add(@RequestBody @Valid ResourceDto resourceDto){
        resourceService.saveResource(resourceDto);
        return Result.success();
    }
}
