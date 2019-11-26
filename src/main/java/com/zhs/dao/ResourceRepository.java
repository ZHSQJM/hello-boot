package com.zhs.dao;

import com.zhs.entity.Resource;
import com.zhs.vo.ResourceVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 11:02
 * @Description:
 * @version: 1.0
 */
@Repository
public interface ResourceRepository  extends JpaRepository<Resource,Long>, JpaSpecificationExecutor<Resource> {

    /**
     * 通过类目查找相关的资源
     * @param type
     * @return
     */
    List<Resource> findResourceByCategoryTypeAndStatus(Long type,Integer status);



    List<Resource> findResourcesByOpenId(String openId);
    List<Resource> findResourcesByOpenIdAndStatus(String openId,Integer status);


    @Query(value = "select * from  resource where status = 0  ORDER BY records DESC LIMIT 1,3 " ,nativeQuery = true)
    List<Resource> findResourcesByRecordsLimitThree();
}
