package com.zhs.service;

import com.zhs.condition.UserCondition;
import com.zhs.dto.UserDto;
import com.zhs.entity.SysUser;
import com.zhs.vo.PageVo;
import com.zhs.vo.UserVo;
import org.springframework.data.domain.Page;

import java.util.List;

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


    /**
     * 带条件查询所有
     * @param userCondition
     * @return
     */
    List<SysUser> findAll(UserCondition userCondition);



    PageVo findPage(UserCondition userCondition, int page, int pageSize);


    /**
     * 给单个用户设置角色
     * @param userId
     * @param roleIds
     */
    void saveRolesByUserId(long userId,List<Long> roleIds);


}
