package com.zhs.service.impl;

import com.zhs.dao.BannerRepository;
import com.zhs.dto.BannerDto;
import com.zhs.entity.Banner;
import com.zhs.service.IBannerSerivce;
import com.zhs.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/10/30 16:16
 * @package: com.zhs.service.impl
 * @description:
 */
@Service
@Slf4j
public class BannerServiceImpl implements IBannerSerivce {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Override
    public void add(BannerDto bannerDto) {

        Banner banner = new Banner();
        BeanUtils.copyProperties(bannerDto,banner);
        banner.setStatus(0);
        banner.setCreateTime(new Date());
        banner.setUpdateTime(new Date());
        banner.setId(snowflakeIdWorker.nextId());
        bannerRepository.save(banner);
    }

    @Override
    public List<Banner> getBanner(Integer status) {
        final List<Banner> byStatusaAndoOrderByCreateTime = bannerRepository.findByStatus(status);
        return byStatusaAndoOrderByCreateTime;
    }
}
