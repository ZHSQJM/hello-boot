package com.zhs.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/1 17:38
 * @Description:
 * @version: 1.0
 */
public interface IUploadService {


    void uploadFile( MultipartFile file);
}
