package com.zhs.dao;

import com.zhs.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/10/30 16:03
 * @package: com.zhs.dao
 * @description:
 */
public interface BannerRepository  extends JpaRepository<Banner,Long>, JpaSpecificationExecutor<Banner> {


    List<Banner>  findByStatus(Integer status);
}
