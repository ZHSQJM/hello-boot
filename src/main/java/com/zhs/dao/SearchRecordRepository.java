package com.zhs.dao;

import com.zhs.entity.Category;
import com.zhs.entity.SearchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @project: study-resource
 * @author: zhs
 * @date: 2019/11/24 15:13
 * @package: com.zhs.dao
 * @description:
 */
public interface SearchRecordRepository extends JpaRepository<SearchRecord,Long>, JpaSpecificationExecutor<SearchRecord> {


}
