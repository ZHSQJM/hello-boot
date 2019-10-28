package com.zhs.controller;

import com.zhs.condition.UserCondition;
import com.zhs.dto.CategoryDto;
import com.zhs.dto.UserDto;
import com.zhs.enums.ResultCode;
import com.zhs.service.ICategoryService;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 10:56
 * @Description:
 * @version: 1.0
 */

@RestController
@Slf4j
@Api(description = "类目的API接口")
@RequestMapping("/v1/api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    @ApiOperation(value = "新增类目",notes = "新增类目")
    public Result add(@RequestBody @Valid CategoryDto categoryDto){
        categoryService.saveCategory(categoryDto);
        return Result.success();
    }

    @GetMapping("/find")
    @ApiOperation(value = "获取类目分页",notes = "获取类目分页(分页)")
    public Result findPage(){
        return Result.success(ResultCode.SUCCESS,categoryService.findAll());
    }
}
