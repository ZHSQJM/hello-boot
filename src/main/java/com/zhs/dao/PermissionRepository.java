package com.zhs.dao;

import com.zhs.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/17 16:16
 * @Description:
 * @version: 1.0
 */
public interface PermissionRepository extends JpaRepository<SysPermission,Long>, JpaSpecificationExecutor<SysPermission>{
}
