package com.zhs.service;

import com.zhs.dto.UserDto;
import com.zhs.entity.SysUser;
import com.zhs.vo.UserVo;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:09
 * @Description:
 * @version: 1.0
 */
public interface IUserService {

    SysUser findUserByUserName(String userName);

    /**
     * 保存用户
     * @param userDto
     */
    void saveUser(UserDto userDto);

    /**
     * 删除用户  这里的删除是逻辑删除  实际上是修改状态
     * @param id
     */
    void deleteUser(Long id);


    /**
     * 修改用户
     * @param userDto
     */
    void  updateUser(UserDto userDto);
    /**
     * 通过用户名查找用户
     * @param id
     * @return
     */
    UserVo findUserById(Long id);
}
