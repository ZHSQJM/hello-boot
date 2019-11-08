package com.zhs.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhs
 * @Title: MockApi
 * @ProjectName hello-boot
 * @Description: TODO
 * @date 2019/10/31 19:41
 */

@RestController
@Slf4j
@Api(description = "小程序模拟的API接口")
@RequestMapping("/v1/api/mock")
public class MockApi {
}
