package com.zhs.service.impl;

import com.zhs.bo.OssUploadImgProvider;
import com.zhs.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/1 17:39
 * @Description:
 * @version: 1.0
 */
@Service
@Slf4j
public class UploadServiceImpl implements IUploadService {



    @Autowired
    private OssUploadImgProvider ossUploadImgProvider;

    @Override
    public String  uploadFile(MultipartFile file) throws IOException {

        String url=ossUploadImgProvider.UploadFile(file.getInputStream(),file.getContentType(),file.getOriginalFilename());


        System.out.println(url);
        return url;


    }


}
