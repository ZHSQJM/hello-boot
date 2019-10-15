package com.zhs.service.impl;

import com.zhs.dao.UserRepository;
import com.zhs.dto.UserDto;
import com.zhs.entity.SysUser;
import com.zhs.exception.ZhsException;
import com.zhs.service.IUserService;
import com.zhs.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:09
 * @Description:
 * @version: 1.0
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public SysUser findUserByUserName(String userName) {
        return userRepository.findSysUserByUserName(userName);
    }

    @Override
    public void saveUser(UserDto userDto) {
        SysUser sysUserByUserName = userRepository.findSysUserByUserName(userDto.getUserName());
        if(sysUserByUserName!=null){
            throw  new ZhsException("用户名已存在");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto,sysUser);
        sysUser.setStatus(0);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        userRepository.save(sysUser);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<SysUser> optional = userRepository.findById(id);
        SysUser sysUser = optional.isPresent()? optional.get():null;
        if(sysUser==null){
            throw new ZhsException("改用户不存在");
        }
        sysUser.setStatus(1);
        sysUser.setUpdateTime(new Date());
        userRepository.save(sysUser);
    }

    @Override
    public void updateUser(UserDto userDto) {
      if(userDto.getId()==null){
          throw new ZhsException("请传入需要修改的用户");
      }
        Optional<SysUser> optional = userRepository.findById(userDto.getId());
        SysUser sysUser = optional.isPresent()? optional.get():null;
        if(sysUser==null){
            throw new ZhsException("改用户不存在");
        }
        sysUser.setUpdateTime(new Date());
        userRepository.save(sysUser);
    }

    @Override
    public UserVo findUserById(Long id) {
        UserVo userVo = new UserVo();
        SysUser sysUser = userRepository.findById(id).get();
        BeanUtils.copyProperties(sysUser,userVo);
        return userVo;
    }
}
