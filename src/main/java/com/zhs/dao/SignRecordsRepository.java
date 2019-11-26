package com.zhs.dao;

import com.zhs.entity.Category;
import com.zhs.entity.SignRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/11/15 22:57
 * @package: com.zhs.dao
 * @description:
 */
public interface SignRecordsRepository extends JpaRepository<SignRecords,Integer>, JpaSpecificationExecutor<SignRecords>{


    SignRecords findByDateTimeAndOpenid(String date,String openId);


}
