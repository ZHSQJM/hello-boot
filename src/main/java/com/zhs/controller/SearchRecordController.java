package com.zhs.controller;

import com.zhs.entity.SearchRecord;
import com.zhs.service.ISearchRecordService;
import com.zhs.service.ISignRecordsService;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: study-resource
 * @author: zhs
 * @date: 2019/11/24 15:17
 * @package: com.zhs.controller
 * @description:
 */

@RestController
@Slf4j
@Api(description = "搜索记录的API接口")
@RequestMapping("/v1/api/search")
public class SearchRecordController {

    @Autowired
    private ISearchRecordService searchRecordService;

    @PostMapping
    @ApiOperation(value = "记录搜索记录",notes = "记录搜索记录")
    public Result search(@RequestBody  SearchRecord searchRecord){
        searchRecordService.add(searchRecord);
        return Result.success();
    }

}
