package com.zhs.controller;

import com.zhs.service.IUploadService;
import com.zhs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/14 16:27
 * @Description:
 * @version: 1.0
 */
@CrossOrigin
@RestController
@Slf4j
@Api(description = "上传文件")
@RequestMapping("/v1/api/upload")
public class UploadController {


    @Autowired
    private IUploadService uploadService;

    @PostMapping
    @ApiOperation(value = "上传文件至阿里云",notes = "上传文件至阿里云")
    public Result fileUpload(@RequestParam(value = "imagefile", required = false) MultipartFile imagefile) throws IOException {
      //  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获取上传的文件
       // MultipartFile multiFile = multipartRequest.getFile("file");
        return Result.success(uploadService.uploadFile(imagefile));
    }
}
