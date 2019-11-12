package com.zhs.service;

import com.zhs.condition.ResourceCondition;
import com.zhs.condition.RoleCondition;
import com.zhs.dto.ResourceDto;
import com.zhs.entity.Resource;
import com.zhs.entity.SysUser;
import com.zhs.vo.PageVo;
import com.zhs.vo.ResourceVo;
import com.zhs.vo.RoleVo;
import com.zhs.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.List;
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
    ResourceVo findResourceById(Long id,String openId);

    List<Resource> getResourceByCategoryId(Long id,Integer status);

    ResourceVo getdetail(Long id);

    List<ResourceVo> getResourceByOpenId(String openId,Integer status);


    /**分页获取*/
    Page<Resource> findPage(ResourceCondition resourceCondition, int page, int pageSize);
}
