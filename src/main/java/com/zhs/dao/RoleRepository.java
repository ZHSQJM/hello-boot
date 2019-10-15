package com.zhs.dao;

import com.zhs.entity.SysRole;
import com.zhs.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:05
 * @Description:
 * @version: 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<SysRole,Long> {



}
