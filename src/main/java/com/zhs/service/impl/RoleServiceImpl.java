package com.zhs.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhs.condition.RoleCondition;
import com.zhs.dao.RoleRepository;
import com.zhs.dto.RoleDto;
import com.zhs.entity.SysRole;
import com.zhs.service.IRoleService;
import com.zhs.utils.SnowflakeIdWorker;
import com.zhs.vo.PageVo;
import com.zhs.vo.RoleVo;
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

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


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

    @Override
    public PageVo<RoleVo> findPage(RoleCondition roleCondition, int page, int pageSize) {
//        QSysRole sysRole = QSysRole.sysRole;
//
//        //初始化组装条件(类似where 1=1)
//        com.querydsl.core.types.Predicate predicate = sysRole.isNotNull().or(sysRole.isNull());
//        //执行动态条件拼装
//        //执行动态条件拼装
//        predicate = roleCondition.getName() == null ? predicate : ExpressionUtils.and(predicate, sysRole.name.like("%"+roleCondition.getName()+"%"));
//        predicate = roleCondition.getStatus() == null ? predicate : ExpressionUtils.and(predicate, sysRole.status.eq(roleCondition.getStatus()));
//        Pageable pageable = PageRequest.of(page-1,pageSize);
//        //直接返回
//
//        List<RoleVo> collect = jpaQueryFactory
//                //投影只去部分字段
//                .select(
//                        sysRole.id,
//                        sysRole.name,
//                        sysRole.description,
//                        sysRole.status,
//                        sysRole.createTime,
//                        sysRole.updateTime
//                )
//                .from(sysRole)
//                //联合查询
//          //      .join(sysRole.users, sysUser)
//                .where(predicate)
//                .fetch()
//                //lambda开始
//                .stream()
//                .map(tuple ->
//                        //需要做类型转换，所以使用map函数非常适合
//                        RoleVo.builder()
//                                .id(tuple.get(sysRole.id))
//                                .name(tuple.get(sysRole.name))
//                                .description(tuple.get(sysRole.description))
//                                .status(tuple.get(sysRole.status))
//                                .createTime(tuple.get(sysRole.createTime))
//                                .updateTime(tuple.get(sysRole.updateTime))
//                                .build()
//                )
//                .collect(Collectors.toList());
//        PageImpl<RoleVo> roleVos = new PageImpl<>(collect, pageable, collect.size());
//        return new PageVo(roleVos.getTotalPages(),roleVos.getContent());
        return null;
    }
}
