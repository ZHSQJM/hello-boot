package com.zhs.controller;

import com.zhs.Result;
import com.zhs.dto.UserDto;
import com.zhs.enums.ResultCode;
import com.zhs.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:29
 * @Description:
 * @version: 1.0
 */
@Api(description = "用户的API接口")
@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    @ApiOperation(value = "新增用户",notes = "新增用户")
    public Result add(@RequestBody @Valid UserDto userDto){
        userService.saveUser(userDto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户",notes = "删除用户")
    public Result delete(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
        return Result.success(ResultCode.SUCCESS);
    }

    @PutMapping
    @ApiOperation(value = "修改用户",notes = "修改用户")
    public Result update(@RequestBody @Valid UserDto userDto){
        userService.updateUser(userDto);
        return Result.success(ResultCode.SUCCESS);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID获取用户",notes = "根据ID获取用户")
    public Result getUserById(@PathVariable(name = "id") Long id){
        return Result.success(ResultCode.SUCCESS,userService.findUserById(id));
    }


}
