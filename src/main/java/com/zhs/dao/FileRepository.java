package com.zhs.dao;

import com.zhs.entity.SysFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/18 11:56
 * @Description:
 * @version: 1.0
 */
public interface FileRepository  extends JpaRepository<SysFile, Long> {
}
