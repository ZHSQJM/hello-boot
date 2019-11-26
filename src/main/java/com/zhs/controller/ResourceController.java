package com.zhs.controller;

import com.zhs.condition.ResourceCondition;
import com.zhs.condition.RoleCondition;
import com.zhs.dto.CategoryDto;
import com.zhs.dto.ResourceDto;
import com.zhs.entity.Resource;
import com.zhs.enums.ResultCode;
import com.zhs.service.IResourceService;
import com.zhs.utils.Result;
import com.zhs.vo.ResourceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("resource-by-category-id/{id}")
    @ApiOperation(value = "根据类型获取资源",notes = "根据类型获取资源")
    public Result getResourceByCategoryId(@PathVariable("id") Long id,Integer status){
       List<Resource> list = resourceService.getResourceByCategoryId(id,status);
        return Result.success(list);
    }


    @GetMapping("get-detail/{id}")
    @ApiOperation(value = "根据类型获取资源",notes = "根据类型获取资源")
    public Result getdetail(@PathVariable("id") Long id){
        ResourceVo resourceVo = resourceService.getdetail(id);
        return Result.success(resourceVo);
    }
    @GetMapping("get-resource")
    @ApiOperation(value = "根据openID获取资源",notes = "根据openID获取资源")
    public Result getResourceByOpenId(@RequestParam(required = true,name = "openId") String openId,Integer status){
        List<ResourceVo> list = resourceService.getResourceByOpenId(openId,status);
        return Result.success(list);
    }


    @GetMapping("/find-all-page")
    @ApiOperation(value = "根据条件获取所有的资源数据数据(分页)",notes = "根据条件获取所有的资源数据数据(分页)")
    public Result findPage(ResourceCondition resourceCondition, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize){
        return Result.success(resourceService.findPage(resourceCondition,page,pageSize));
    }

    @GetMapping("/find-resource-by-id")
    @ApiOperation(value = "获取单个资源的详细信息",notes = "获取单个资源的详细信息(分页)")
    public Result findResourceById(String openId,Long id){
        return Result.success(resourceService.findResourceById(id,openId));
    }


    @GetMapping("/get-hot-resource")
    @ApiOperation(value = "获取热门资源三个")
    public  Result findHotResource(){

        return Result.success(resourceService.getHotResource());
    }
}
