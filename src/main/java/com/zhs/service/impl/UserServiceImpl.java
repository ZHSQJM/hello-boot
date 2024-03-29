package com.zhs.service.impl;

import com.zhs.condition.UserCondition;
import com.zhs.dao.RoleRepository;
import com.zhs.dao.UserRepository;
import com.zhs.dto.UserDto;
import com.zhs.entity.SysRole;
import com.zhs.entity.SysUser;
import com.zhs.enums.ResultEnum;
import com.zhs.exception.ZhsException;
import com.zhs.service.IUserService;
import com.zhs.utils.SnowflakeIdWorker;
import com.zhs.vo.PageVo;
import com.zhs.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:09
 * @Description:
 * @version: 1.0
 */
@Service(value = "userService")
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    @Override
    @Cacheable(key = "#p0",unless="#result == null")
    public SysUser findUserByUserName(String userName) {
        return userRepository.findSysUserByUsername(userName);
    }

    @Override
    @CacheEvict(key = "list")
    public void saveUser(UserDto userDto) {
        SysUser sysUserByUserName = userRepository.findSysUserByUsername(userDto.getUsername());
        if(sysUserByUserName!=null){
            throw  new ZhsException(ResultEnum.USER_HAS_EXIT);
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto,sysUser);
        sysUser.setId(snowflakeIdWorker.nextId());
        sysUser.setStatus(0);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        userRepository.save(sysUser);
    }

    @Override
    @CacheEvict(key = "#p0")
    public void deleteUser(Long id) {
        Optional<SysUser> optional = userRepository.findById(id);
        SysUser sysUser = optional.isPresent()? optional.get():null;
        if(sysUser==null){
            throw new ZhsException(ResultEnum.USER_NOT_FOUNT);
        }
        sysUser.setStatus(1);
        sysUser.setUpdateTime(new Date());
        userRepository.save(sysUser);
    }

    @Override
    @CachePut(key = "#p0")
    public void updateUser(UserDto userDto) {
      if(userDto.getId()==null){
          throw new ZhsException(ResultEnum.USER_NOT_FOUNT);
      }
        Optional<SysUser> optional = userRepository.findById(userDto.getId());
        SysUser sysUser = optional.isPresent()? optional.get():null;
        if(sysUser==null){
            throw new ZhsException(ResultEnum.USER_HAS_EXIT);
        }
        sysUser.setUpdateTime(new Date());
        userRepository.save(sysUser);
    }

    @Override
    @Cacheable(key = "#p0",unless="#result==null")
    public UserVo findUserById(Long id) {
        UserVo userVo = new UserVo();
        Optional<SysUser> optional = userRepository.findById(id);
        SysUser sysUser = optional.isPresent()?optional.get():null;
        BeanUtils.copyProperties(sysUser,userVo);
        return userVo;
    }

    @Override
    public List<SysUser> findAll(UserCondition userCondition) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userCondition,sysUser);
       return userRepository.findAll(getSpecification(sysUser));
    }

    @Override
    public PageVo<UserVo> findPage(UserCondition userCondition, int page, int pageSize) {
//        QSysUser sysUser = QSysUser.sysUser;
//        QSysRole sysRole = QSysRole.sysRole;
//
//        //初始化组装条件(类似where 1=1)
//        com.querydsl.core.types.Predicate predicate = sysUser.isNotNull().or(sysUser.isNull());
//        //执行动态条件拼装
//        //执行动态条件拼装
//        predicate = userCondition.getUserName() == null ? predicate : ExpressionUtils.and(predicate, sysUser.username.like("%"+userCondition.getUserName()+"%"));
//        predicate = userCondition.getStatus() == null ? predicate : ExpressionUtils.and(predicate, sysUser.status.eq(userCondition.getStatus()));
//        Pageable pageable = PageRequest.of(page-1,pageSize);
//        //直接返回
//
//        List<UserVo> collect = jpaQueryFactory
//                //投影只去部分字段
//                .select(
//                        sysUser.id,
//                        sysUser.username,
//                        sysUser.sex,
//                        sysUser.status,
//                        sysUser.createTime,
//                        sysUser.updateTime,
//                        sysRole.name
//                )
//                .from(sysUser)
//                //联合查询
//                .join(sysUser.roles, sysRole)
//                .where(predicate)
//                .fetch()
//                //lambda开始
//                .stream()
//                .map(tuple ->
//                        //需要做类型转换，所以使用map函数非常适合
//                        UserVo.builder()
//                                .id(tuple.get(sysUser.id))
//                                .username(tuple.get(sysUser.username))
//                                .sex(tuple.get(sysUser.sex))
//                                .status(tuple.get(sysUser.status))
//                                .sex(tuple.get(sysUser.sex))
//                                .sex(tuple.get(sysUser.sex))
//                                .createTime(tuple.get(sysUser.createTime))
//                                .updateTime(tuple.get(sysUser.updateTime))
//                                .roleName(tuple.get(sysRole.name))
//                                .build()
//                )
//                .collect(Collectors.toList());
//        PageImpl<UserVo> userVos = new PageImpl<>(collect, pageable, collect.size());
//        return  new PageVo(userVos.getTotalPages(),userVos.getContent());
        return null;
    }

    @Override
    public void saveRolesByUserId(long userId, List<Long> roleIds) {

        Optional<SysUser> optional = userRepository.findById(userId);
        SysUser sysUser = optional.isPresent()? optional.get():null;
        if(sysUser==null){
            throw new ZhsException(ResultEnum.USER_NOT_FOUNT);
        }

        List<SysRole> roles = roleRepository.findAllById(roleIds);
        sysUser.setRoles(roles);

        userRepository.save(sysUser);
    }


    public Specification getSpecification(SysUser sysUser){
       return (a,b,c)-> {
           List<Predicate> list = new ArrayList<>(16);
           if (sysUser.getUsername() != null && !"".equals(sysUser.getUsername())) {
               //用户名模糊查询
               Predicate predicate = c.like(a.get("userName").as(String.class), "%" + sysUser.getUsername() + "%");
               list.add(predicate);
           }
           if (sysUser.getStatus() != null && !"".equals(sysUser.getStatus())) {
               //用户名模糊查询
               Predicate predicate = c.equal(a.get("status").as(Integer.class), sysUser.getStatus());
               list.add(predicate);
           }
           Predicate[] parr = new Predicate[list.size()];
           //把list专场数组
           list.toArray(parr);
           return c.and(parr);
       };

    }

}


