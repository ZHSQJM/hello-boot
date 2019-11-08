package com.zhs.dao;

import com.zhs.entity.WeiXinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 16:30
 * @Description:
 * @version: 1.0
 */
@Repository
public interface WeixinUserReposotory extends JpaRepository<WeiXinUser,String>, JpaSpecificationExecutor<WeiXinUser> {


    WeiXinUser findByOpenId(String openId);
}
