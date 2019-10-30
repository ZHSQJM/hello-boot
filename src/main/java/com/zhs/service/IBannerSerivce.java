package com.zhs.service;

import com.zhs.dto.BannerDto;
import com.zhs.entity.Banner;

import java.util.List;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/10/30 16:15
 * @package: com.zhs.service
 * @description:
 */
public interface IBannerSerivce {

    void add(BannerDto bannerDto);

    List<Banner> getBanner(Integer status);
}
