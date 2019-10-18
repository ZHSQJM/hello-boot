package com.zhs.controller;

import com.zhs.utils.Result;
import com.zhs.condition.FileCondition;
import com.zhs.dto.FileDto;
import com.zhs.enums.ResultCode;
import com.zhs.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/18 12:01
 * @Description:
 * @version: 1.0
 */
@Api(description = "文件的API接口")
@RestController
@RequestMapping("/v1/api/file")
public class FileController {


    @Autowired
    private IFileService fileService;

    @PostMapping
    @ApiOperation(value = "新增文件",notes = "新增文件")
    public Result add(@RequestBody @Valid FileDto fileDto){
        fileService.saveUser(fileDto);
        return Result.success();
    }


    @GetMapping("/find-all")
    @ApiOperation(value = "根据条件获取所有的文件数据",notes = "根据条件获取所有的文件数据")
    public Result findAll( FileCondition fileCondition){
        return Result.success(ResultCode.SUCCESS,fileService.findAll(fileCondition));
    }

    @GetMapping("/find-all-page")
    @ApiOperation(value = "根据条件获取所有的用户数据(分页)",notes = "根据条件获取所有的用户数据(分页)")
    public Result findPage(FileCondition fileCondition, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize){
        return Result.success(ResultCode.SUCCESS,fileService.findAllPage(fileCondition,page,pageSize));
    }

}
