package com.zhs.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 12:14
 * @Description:
 * @version: 1.0
 */
public class ResourceVo {


    /*** 主键*/
    private Long id;

    /*** 资源名称*/
    private String name;

    /*** 描述*/
    @Column(length = 255)
    private String description;

    /*** 积分*/
    @Column(length = 255)
    private Long integral;

    /**下载路径*/
    private String url;

    /**密码*/
    private String password;

    /*** 所属类型*/
    @Column(length = 32)
    private Long categoryType;

    /*** 所属状态*/

    private Integer status;

    /*** 创建时间*/

    private Date createTime;

    /*** 修改时间*/
    private Date updateTime;
}
