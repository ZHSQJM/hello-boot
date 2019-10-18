package com.zhs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/18 11:57
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    /**文件名称*/
    private String fileName;

    /**文件大小*/
    private String fileSize;

    /**文件存储地址*/
    private String url;

    /**文件所属用户*/
    private String userId;

    /**文件所属类型  0 图片 1 文件 2视频 3 音频*/
    private Integer type;
}
