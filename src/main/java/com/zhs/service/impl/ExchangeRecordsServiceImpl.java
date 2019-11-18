package com.zhs.service.impl;

import com.zhs.dao.ExchangeRecordsRepository;
import com.zhs.dao.ResourceRepository;
import com.zhs.dao.WeixinUserReposotory;
import com.zhs.entity.ExchangeRecords;
import com.zhs.entity.Resource;
import com.zhs.entity.WeiXinUser;
import com.zhs.enums.ResultEnum;
import com.zhs.exception.ZhsException;
import com.zhs.service.IExchangeRecordsService;
import com.zhs.utils.SnowflakeIdWorker;
import com.zhs.vo.ExchangeRecordsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 17:15
 * @Description: 兑换记录的
 * @version: 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ExchangeRecordsServiceImpl implements IExchangeRecordsService {

    @Autowired
    private ExchangeRecordsRepository exchangeRecordsRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private WeixinUserReposotory weixinUserReposotory;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    @Override
    public String add(ExchangeRecords exchangeRecords) {
        WeiXinUser weixinUser = weixinUserReposotory.findById(exchangeRecords.getUserId()).orElseThrow(() -> new ZhsException(ResultEnum.USER_NOT_FOUNT));
        Resource resource = resourceRepository.findById(exchangeRecords.getResourceId()).orElseThrow(() -> new ZhsException(ResultEnum.RESOURCE_NOT_FOUND));
        Integer needIntegral = exchangeRecords.getIntegral();
        Integer hasIntegral = weixinUser.getIntegral();
        if(hasIntegral<needIntegral){
            throw  new ZhsException(ResultEnum.INTEGRAL_INSUFFICIENT);
        }
        //兑换者扣除积分
        Integer nowIntegral =  hasIntegral - needIntegral;
        weixinUser.setIntegral(nowIntegral);
        weixinUserReposotory.save(weixinUser);
        exchangeRecords.setStatus(0);
        exchangeRecords.setId(snowflakeIdWorker.nextId());
        exchangeRecordsRepository.save(exchangeRecords);

        //资源下载数加+1
        resource.setRecords(resource.getRecords()+1);
        resourceRepository.save(resource);

        //资源上传者加上该积分
        final String openId = resource.getOpenId();
        final WeiXinUser byOpenId = weixinUserReposotory.findByOpenId(openId);
       Integer newInteger =  byOpenId.getIntegral()+ needIntegral;
        byOpenId.setIntegral(needIntegral);
        weixinUserReposotory.save(byOpenId);

        return resource.getPassword();
    }

    @Override
    public Page<ExchangeRecordsVo> getExchangerRecords(String openId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<ExchangeRecords> allByUserId = exchangeRecordsRepository.findAllByUserId(openId);
        List<ExchangeRecordsVo> list = new ArrayList<>(16);
        for (ExchangeRecords exchangeRecords : allByUserId) {
            Resource resource = resourceRepository.findById(exchangeRecords.getResourceId()).orElseThrow(() -> new ZhsException(ResultEnum.RESOURCE_NOT_FOUND));
            ExchangeRecordsVo exchangeRecordsVo = new ExchangeRecordsVo();
            BeanUtils.copyProperties(exchangeRecords,exchangeRecordsVo);
            exchangeRecordsVo.setName(resource.getName());
            exchangeRecordsVo.setDescription(resource.getDescription());
            exchangeRecordsVo.setPhothUrl(resource.getPhothUrl());
            exchangeRecordsVo.setId(resource.getId());
            list.add(exchangeRecordsVo);
        }
        PageImpl<ExchangeRecordsVo> exchangeRecordsVos = new PageImpl<>(list, pageable, list.size());
        return exchangeRecordsVos;
    }

//    public Specification getSpecification(String openId){
//        return (a,b,c)-> {
//            List<Predicate> list = new ArrayList<>(16);
//            if (openId != null && !"".equals(openId)) {
//                //用户名模糊查询
//                Predicate predicate = c.equal(a.get("openId").as(Integer.class), openId);
//                list.add(predicate);
//            }
//
//            Predicate[] parr = new Predicate[list.size()];
//            //把list专场数组
//            list.toArray(parr);
//            return c.and(parr);
//        };
//
//    }
}
