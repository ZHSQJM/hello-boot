package com.zhs.controller;

import com.zhs.dto.CategoryDto;
import com.zhs.entity.ExchangeRecords;
import com.zhs.service.IExchangeRecordsService;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/11 12:28
 * @Description:
 * @version: 1.0
 */

@CrossOrigin
@RestController
@Slf4j
@Api(description = "类目的API接口")
@RequestMapping("/v1/api/exchanger")
public class ExchanerRecordsController {

    @Autowired
   private IExchangeRecordsService exchangeRecordsService;

    @PostMapping
    @ApiOperation(value = "兑换",notes = "兑换")
    public Result exchangerRecords(@RequestBody @Valid ExchangeRecords exchangeRecords){
        return Result.success( exchangeRecordsService.add(exchangeRecords));
    }

    @GetMapping("get-exchanger-records")
    @ApiOperation(value = "兑换记录",notes = "兑换记录")
    public Result getExchangerRecords(String openId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize){
        return Result.success(exchangeRecordsService.getExchangerRecords(openId,page,pageSize));
    }

}
