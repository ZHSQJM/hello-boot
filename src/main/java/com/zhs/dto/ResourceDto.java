package com.zhs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 11:00
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceDto {


    /*** 主键*/
    private Long id;

    /*** 资源名称*/
    private String name;

    /*** 所属用户*/
    private String openId;

    /*** 描述*/
    private String description;

    /**封面图片*/
    private String photoUrl;

    private Integer status;

    /**下载路径*/
    private String url;

    /**密码*/
    private String password;

    /*** 积分*/
    private Integer integral;

    /*** 所属类型*/
    private Long categoryType;

}
