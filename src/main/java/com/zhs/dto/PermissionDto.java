package com.zhs.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/21 9:13
 * @Description:
 * @version: 1.0
 */
public class PermissionDto {


    /**主键*/
    private Long id;

    /**权限名称*/
    @NotNull(message = "权限名称不能为空")
    private String name;

    /**地址*/
    @NotNull(message = "地址不能为空")
    private String url;

    /**上级ID*/
    @NotNull(message = "描述不能为空")
    private int pid;

    /**描述*/
    @NotNull(message = "描述不能为空")
    private String description;

    /**类型* 0 菜单  1接口地址  2 外链*/
    @NotNull(message = "描述不能为空")
    private Integer type;


}
