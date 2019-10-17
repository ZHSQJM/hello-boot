package com.zhs.service.impl;

import com.zhs.dao.RoleRepository;
import com.zhs.dao.UserRepository;
import com.zhs.dto.RoleDto;
import com.zhs.dto.UserDto;
import com.zhs.entity.SysRole;
import com.zhs.entity.SysUser;
import com.zhs.exception.ZhsException;
import com.zhs.service.IRoleService;
import com.zhs.service.IUserService;
import com.zhs.utils.SnowflakeIdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:09
 * @Description:
 * @version: 1.0
 */
@Service(value = "roleService")
public class RoleServiceImpl implements IRoleService {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    @Override
    public void saveRole(RoleDto roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto,sysRole);
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(new Date());
        sysRole.setStatus(0);
        sysRole.setId(snowflakeIdWorker.nextId());
        roleRepository.save(sysRole);
    }
}
