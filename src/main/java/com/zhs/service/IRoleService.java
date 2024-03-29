package com.zhs.service;

import com.zhs.condition.RoleCondition;
import com.zhs.dto.RoleDto;
import com.zhs.dto.UserDto;
import com.zhs.entity.SysUser;
import com.zhs.vo.PageVo;
import com.zhs.vo.RoleVo;
import org.springframework.data.domain.Page;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:09
 * @Description:
 * @version: 1.0
 */
public interface IRoleService {

    void saveRole(RoleDto roleDto);

    PageVo<RoleVo> findPage(RoleCondition roleCondition, int page, int pageSize);
}
