package com.zhs.controller;

import com.zhs.Result;
import com.zhs.condition.UserCondition;
import com.zhs.dto.RoleDto;
import com.zhs.dto.UserDto;
import com.zhs.service.IRoleService;
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
@Api(description = "角色的API接口")
@RestController
@RequestMapping("/v1/api/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @PostMapping
    @ApiOperation(value = "新增角色",notes = "新增角色")
    public Result add(@RequestBody @Valid RoleDto roleDto){
        roleService.saveRole(roleDto);
        return Result.success();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "获取单个角色",notes = "获取单个角色")
    public Result getUserById(@PathVariable ("id") Long id){
        System.out.println();
        return Result.success();
    }


}
