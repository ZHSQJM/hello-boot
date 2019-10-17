package com.zhs.service.impl;

import com.zhs.condition.UserCondition;
import com.zhs.dao.RoleRepository;
import com.zhs.dao.UserRepository;
import com.zhs.dto.UserDto;
import com.zhs.entity.SysRole;
import com.zhs.entity.SysUser;
import com.zhs.exception.ZhsException;
import com.zhs.service.IUserService;
import com.zhs.utils.SnowflakeIdWorker;
import com.zhs.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private RoleRepository roleRepository;

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
       return userRepository.findAll(new Specification<SysUser>() {

            /**
             *
             * @param root 跟对象  也就是也要把条件分装到那个对象中，where类名 = user》getUsername
             * @param criteriaQuery 分装的都是查询的换剪子  比如 groupby order by等等
             * @param criteriaBuilder 用来分装条件对象 如果直接返回null 表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {


                List<Predicate> list = new ArrayList<>(16);
                if (sysUser.getUserName() != null && !"".equals(sysUser.getUserName())) {
                    //用户名模糊查询
                    Predicate predicate = criteriaBuilder.like(root.get("userName").as(String.class), "%" + sysUser.getUserName() + "%");
                    list.add(predicate);
                }

                if (sysUser.getStatus() != null && !"".equals(sysUser.getStatus())) {
                    //用户名模糊查询
                    Predicate predicate = criteriaBuilder.equal(root.get("status").as(Integer.class), sysUser.getStatus());
                    list.add(predicate);
                }

                Predicate[] parr = new Predicate[list.size()];

                //把list专场数组
                list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        });



    }

    @Override
    public Page<SysUser> findPage(UserCondition userCondition, int page, int pageSize) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userCondition,sysUser);
        Pageable pageable = PageRequest.of(page-1,pageSize);
        return userRepository.findAll(new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>(16);
                if (sysUser.getUserName() != null && !"".equals(sysUser.getUserName())) {
                    //用户名模糊查询
                    Predicate predicate = criteriaBuilder.like(root.get("userName").as(String.class), "%" + sysUser.getUserName() + "%");
                    list.add(predicate);
                }

                if (sysUser.getStatus() != null && !"".equals(sysUser.getStatus())) {
                    //用户名模糊查询
                    Predicate predicate = criteriaBuilder.equal(root.get("status").as(Integer.class), sysUser.getStatus());
                    list.add(predicate);
                }

                Predicate[] parr = new Predicate[list.size()];

                //把list专场数组
                list.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        }, pageable);


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
}


