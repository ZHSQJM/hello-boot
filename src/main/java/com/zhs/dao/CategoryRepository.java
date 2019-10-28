package com.zhs.dao;

import com.zhs.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 10:01
 * @Description:
 * @version: 1.0
 */
public interface CategoryRepository extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category> {


    /**
     * 根据状态获取分类
     * @param status
     * @return
     */
    List<Category> findAllByStatus(Integer status);
}
