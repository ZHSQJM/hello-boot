package com.zhs.dao;

import com.zhs.condition.UserCondition;
import com.zhs.entity.SysUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:05
 * @Description:
 * @version: 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor<SysUser>, QuerydslPredicateExecutor<SysUser> {


    SysUser findSysUserByUserName(String userName);


    /**
     *  多表查询+分页，自定义查询返回的结果集用 List<Map<String,object>> 接收
     * @param userCondition
     * @param pageable
     * @return
     * @Query("select u from User u where u.firstname = :#{#customer.firstname}")
     * List<User> findUsersByCustomersFirstname(@Param("customer") Customer customer);
     * SELECT id,user_name,sex,`status`,create_time, update_time FROM tb_sys_user
     */
//    @Query(value = "SELECT a.id,a.user_name,a.sex,a.`status`,a.create_time,a.update_time FROM tb_sys_user as a  where a.status = :#{userCondition.status}}",
//            nativeQuery = true)
//     List<Map<String,Object>> userAndItemListMap(@Param("userCondition")UserCondition userCondition, Pageable pageable);





}
