package com.zhs.service.impl;

import com.zhs.dao.CategoryRepository;
import com.zhs.dao.ResourceRepository;
import com.zhs.dao.WeixinUserReposotory;
import com.zhs.dto.ResourceDto;
import com.zhs.entity.Category;
import com.zhs.entity.Resource;
import com.zhs.entity.WeiXinUser;
import com.zhs.exception.ZhsException;
import com.zhs.service.IResourceService;
import com.zhs.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 11:04
 * @Description:
 * @version: 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WeixinUserReposotory weixinUserReposotory;

    @Override
    public void saveResource(ResourceDto resourceDto) {
        Optional<Category> optional = categoryRepository.findById(resourceDto.getCategoryType());
        if(!optional.isPresent()){
            throw new ZhsException("没有该类目,请重新选择");
        }

        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceDto,resource);
        resource.setCreateTime(new Date()).setUpdateTime(new Date()).setId(snowflakeIdWorker.nextId());

        String openId = resourceDto.getOpenId();
        Optional<WeiXinUser> userOptional = weixinUserReposotory.findById(openId);

        if(!userOptional.isPresent()){
           throw new ZhsException("用户不存在");
        }
        WeiXinUser weixinUser = userOptional.get();
        Integer hasIntegral = weixinUser.getIntegral();
        Integer totalIntegral = hasIntegral + resource.getIntegral();
        weixinUser.setIntegral(totalIntegral);
        weixinUserReposotory.save(weixinUser);
        resourceRepository.save(resource);
    }

    @Override
    public ResourceDto findResourceById(Long id) {
        Optional<Resource> optional = resourceRepository.findById(id);
        ResourceDto resourceDto = new ResourceDto();
        if(optional.isPresent()){
            Resource resource = optional.get();
           BeanUtils.copyProperties(resource,resourceDto);
        }
        return resourceDto;
    }
}
