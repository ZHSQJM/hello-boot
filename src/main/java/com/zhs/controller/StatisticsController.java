package com.zhs.controller;

import com.zhs.service.IStatisticsService;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/14 16:10
 * @Description:
 * @version: 1.0
 */

@CrossOrigin
@RestController
@Slf4j
@Api(description = "统计分析接口")
@RequestMapping("/v1/api/statistics")
public class StatisticsController {


    @Autowired
     private IStatisticsService statisticsService;


    @GetMapping("personal")
    @ApiOperation(value = "根据openId回去当前用户的资源数",notes = "兑换")
    public Result exchangerRecords(String openId){
        return Result.success( statisticsService.getPersonnal(openId));
    }
}
