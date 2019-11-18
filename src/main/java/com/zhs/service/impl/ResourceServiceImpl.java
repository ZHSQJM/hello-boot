package com.zhs.service.impl;

import com.zhs.condition.ResourceCondition;
import com.zhs.dao.CategoryRepository;
import com.zhs.dao.ExchangeRecordsRepository;
import com.zhs.dao.ResourceRepository;
import com.zhs.dao.WeixinUserReposotory;
import com.zhs.dto.ResourceDto;
import com.zhs.entity.*;
import com.zhs.enums.ResultEnum;
import com.zhs.exception.ZhsException;
import com.zhs.service.IResourceService;
import com.zhs.utils.SnowflakeIdWorker;
import com.zhs.vo.PageVo;
import com.zhs.vo.ResourceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
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

    @Autowired
    private ExchangeRecordsRepository exchangeRecordsRepository;

    @Override
    public void saveResource(ResourceDto resourceDto) {
        Optional<Category> optional = categoryRepository.findById(resourceDto.getCategoryType());
        if(!optional.isPresent()){
            throw new ZhsException(ResultEnum.CATEGORY_NOT_FOUND);
        }

        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceDto,resource);
        resource.setCreateTime(new Date()).setUpdateTime(new Date()).setId(snowflakeIdWorker.nextId());

        String openId = resourceDto.getOpenId();
        Optional<WeiXinUser> userOptional = weixinUserReposotory.findById(openId);

        if(!userOptional.isPresent()){
           throw new ZhsException(ResultEnum.USER_NOT_FOUNT);
        }
        WeiXinUser weixinUser = userOptional.get();
        Integer hasIntegral = weixinUser.getIntegral();
        Integer totalIntegral = hasIntegral + resource.getIntegral();
        resource.setRecords(1);
        weixinUser.setIntegral(totalIntegral);
        weixinUserReposotory.save(weixinUser);
        resourceRepository.save(resource);
    }

    @Override
    public ResourceVo findResourceById(Long id,String openId) {

        ExchangeRecords exchangeRecords = exchangeRecordsRepository.findByUserIdAndResourceId(openId,id);

        Optional<ExchangeRecords> optional = Optional.ofNullable(exchangeRecords);

        Optional<Resource> optional1 = resourceRepository.findById(id);
        ResourceVo resourceVo = new ResourceVo();
        if(optional1.isPresent()){
            Resource resource = optional1.get();
           BeanUtils.copyProperties(resource,resourceVo);
        }

        if(optional.isPresent()){
            return resourceVo;
        }else{
            resourceVo.setPassword(null);
            return resourceVo;
        }
    }

    @Override
    public List<Resource> getResourceByCategoryId(Long id,Integer status) {
        List<Resource> allByCategoryTypeAndStatus = resourceRepository.findResourceByCategoryTypeAndStatus(id, status);
        return allByCategoryTypeAndStatus;
    }

    @Override
    public ResourceVo getdetail(Long id) {
        ResourceVo resourceVo  = new ResourceVo();
        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new ZhsException(ResultEnum.RESOURCE_NOT_FOUND));


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

    @Override
    public Page<Resource> findPage(ResourceCondition resourceCondition, int page, int pageSize) {


        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceCondition,resource);
        Pageable pageable = new PageRequest(page-1, pageSize, Sort.Direction.ASC, "createTime");
        return resourceRepository.findAll(getSpecification(resource), pageable);

    }


    public Specification getSpecification(Resource resourceCondition){
        return (a,b,c)-> {
            List<Predicate> list = new ArrayList<>(16);
            if (resourceCondition.getName() != null && !"".equals(resourceCondition.getName())) {
                //资源名称模糊查询
                Predicate predicate = c.like(a.get("name").as(String.class), "%" + resourceCondition.getName() + "%");
                list.add(predicate);
            }
            if (resourceCondition.getStatus() != null && !"".equals(resourceCondition.getStatus())) {
                //用户名模糊查询
                Predicate predicate = c.equal(a.get("status").as(Integer.class), resourceCondition.getStatus());
                list.add(predicate);
            }
            if (resourceCondition.getOpenId() != null && !"".equals(resourceCondition.getOpenId())) {
                //用户名模糊查询
                Predicate predicate = c.equal(a.get("openId").as(String.class), resourceCondition.getOpenId());
                list.add(predicate);
            }
            if (resourceCondition.getCategoryType() != null && !"".equals(resourceCondition.getCategoryType())) {
                //用户名模糊查询
                Predicate predicate = c.equal(a.get("categoryType").as(String.class), resourceCondition.getCategoryType());
                list.add(predicate);
            }
            Predicate[] parr = new Predicate[list.size()];
            //把list专场数组
            list.toArray(parr);
            return c.and(parr);
        };

    }
}
