package com.zhs.service;

import com.zhs.dto.RoleDto;
import com.zhs.dto.UserDto;
import com.zhs.entity.SysUser;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:09
 * @Description:
 * @version: 1.0
 */
public interface IRoleService {

    void saveRole(RoleDto roleDto);
}
