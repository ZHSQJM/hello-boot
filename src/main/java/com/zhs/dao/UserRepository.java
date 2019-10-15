package com.zhs.dao;

import com.zhs.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:05
 * @Description:
 * @version: 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor<SysUser> {


    SysUser findSysUserByUserName(String userName);



}
