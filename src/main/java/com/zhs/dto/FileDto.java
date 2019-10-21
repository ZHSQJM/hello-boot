package com.zhs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "文件名称不能为空")
    private String fileName;

    /**文件大小*/
    @NotNull(message = "文件大小不能为空")
    private String fileSize;

    /**文件存储地址*/
    @NotNull(message = "文件地址不能为空")
    private String url;

    /**文件所属用户*/
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**文件所属类型  0 图片 1 文件 2视频 3 音频*/
    @NotNull(message = "文件类型不能为空")
    private Integer type;
}
