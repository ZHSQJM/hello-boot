package com.zhs.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhs.condition.UserCondition;
import com.zhs.dao.RoleRepository;
import com.zhs.dao.UserRepository;
import com.zhs.dto.UserDto;
import com.zhs.entity.QSysRole;
import com.zhs.entity.QSysUser;
import com.zhs.entity.SysRole;
import com.zhs.entity.SysUser;
import com.zhs.exception.ZhsException;
import com.zhs.service.IUserService;
import com.zhs.utils.MapObjUtil;
import com.zhs.utils.SnowflakeIdWorker;
import com.zhs.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


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
        sysUser.setId(snowflakeIdWorker.nextId());
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
    public Page<UserVo> findPage(UserCondition userCondition, int page, int pageSize) {
        QSysUser sysUser = QSysUser.sysUser;
        QSysRole sysRole = QSysRole.sysRole;

        //初始化组装条件(类似where 1=1)
        com.querydsl.core.types.Predicate predicate = sysUser.isNotNull().or(sysUser.isNull());
        //执行动态条件拼装
        //执行动态条件拼装
        predicate = userCondition.getUserName() == null ? predicate : ExpressionUtils.and(predicate, sysUser.userName.like("%"+userCondition.getUserName()+"%"));
        predicate = userCondition.getStatus() == null ? predicate : ExpressionUtils.and(predicate, sysUser.status.eq(userCondition.getStatus()));
        Pageable pageable = PageRequest.of(page-1,pageSize);
        //直接返回

        List<UserVo> collect = jpaQueryFactory
                //投影只去部分字段
                .select(
                        sysUser.id,
                        sysUser.userName,
                        sysUser.sex,
                        sysUser.status,
                        sysUser.createTime,
                        sysUser.updateTime,
                        sysRole.roleName
                )
                .from(sysUser)
                //联合查询
                .join(sysUser.roles, sysRole)
                .where(predicate)
                .fetch()
                //lambda开始
                .stream()
                .map(tuple ->
                        //需要做类型转换，所以使用map函数非常适合
                        UserVo.builder()
                                .id(tuple.get(sysUser.id))
                                .userName(tuple.get(sysUser.userName))
                                .sex(tuple.get(sysUser.sex))
                                .status(tuple.get(sysUser.status))
                                .sex(tuple.get(sysUser.sex))
                                .sex(tuple.get(sysUser.sex))
                                .createTime(tuple.get(sysUser.createTime))
                                .updateTime(tuple.get(sysUser.updateTime))
                                .roleName(tuple.get(sysRole.roleName))
                                .build()
                )
                .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, collect.size());
    }

    @Override
    public void saveRolesByUserId(long userId, List<Long> roleIds) {

        Optional<SysUser> optional = userRepository.findById(userId);
        SysUser sysUser = optional.isPresent()? optional.get():null;
        if(sysUser==null){
            throw new ZhsException("该用户不存在");
        }

        List<SysRole> roles = roleRepository.findAllById(roleIds);
        sysUser.setRoles(roles);

        userRepository.save(sysUser);
    }


    public Specification getSpecification(SysUser sysUser){
       return (a,b,c)-> {
           List<Predicate> list = new ArrayList<>(16);
           if (sysUser.getUserName() != null && !"".equals(sysUser.getUserName())) {
               //用户名模糊查询
               Predicate predicate = c.like(a.get("userName").as(String.class), "%" + sysUser.getUserName() + "%");
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


