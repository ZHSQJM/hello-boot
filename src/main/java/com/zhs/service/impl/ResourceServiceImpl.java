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
import com.zhs.vo.ResourceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        resource.setRecords(0);
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

    @Override
    public List<Resource> getResourceByCategoryId(Long id,Integer status) {
        List<Resource> allByCategoryTypeAndStatus = resourceRepository.findResourceByCategoryTypeAndStatus(id, status);
        return allByCategoryTypeAndStatus;
    }

    @Override
    public ResourceVo getdetail(Long id) {
        ResourceVo resourceVo  = new ResourceVo();
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new ZhsException("找不到该资源"));

        if(resource.getStatus().equals("1")){
            new ZhsException("资源过期");
        }
        BeanUtils.copyProperties(resource,resourceVo);
        String openId = resource.getOpenId();
        WeiXinUser byOpenId = weixinUserReposotory.findByOpenId(openId);
        resourceVo.setUsername(byOpenId.getNickName());
        return resourceVo;
    }

    @Override
    public List<ResourceVo> getResourceByOpenId(String openId,Integer status) {
        List<ResourceVo> list  = new ArrayList<>(16);
        List<Resource> resourcesByOpenIdaAndStatus = resourceRepository.findResourcesByOpenIdAndStatus(openId, status);
        for (Resource byOpenIdaAndStatus : resourcesByOpenIdaAndStatus) {
            ResourceVo resourceVo = new ResourceVo();
            BeanUtils.copyProperties(byOpenIdaAndStatus,resourceVo);
            list.add(resourceVo);
        }
        return list;
    }
}
