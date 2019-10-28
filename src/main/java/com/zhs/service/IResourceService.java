package com.zhs.service;

import com.zhs.dto.ResourceDto;
import com.zhs.entity.SysUser;
import com.zhs.vo.UserVo;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 11:03
 * @Description:
 * @version: 1.0
 */
public interface IResourceService  {

    /**
     * 保存资源
     * @param resourceDto
     */
    void saveResource(ResourceDto resourceDto);


    /**
     * 获取资源详情
     * @param id
     * @return
     */
    ResourceDto findResourceById(Long id);
}
