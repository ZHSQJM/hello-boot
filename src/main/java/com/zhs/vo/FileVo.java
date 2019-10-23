package com.zhs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/18 12:09
 * @Description:
 * @version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileVo implements Serializable {


    private Long id;

    /**文件名称*/
    private String fileName;

    /**文件大小*/
    private String fileSize;

    /**文件存储地址*/
    private String url;

    /**文件所属用户*/
    private String userName;

    /**文件所属类型  0 图片 1 文件 2视频 3 音频*/
    private Integer type;

    /**创建时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**修改时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
}
