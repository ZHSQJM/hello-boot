package com.zhs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 12:14
 * @Description:
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVo implements Serializable {


    /*** 主键*/
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /*** 资源名称*/
    private String name;

    /*** 描述*/
    @Column(length = 255)
    private String description;

    /*** 积分*/
    @Column(length = 255)
    private Integer integral;

    /**下载路径*/
    private String url;

    /**密码*/
    private String password;

    /*** 所属类型*/
    @Column(length = 32)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long categoryType;

    /*** 所属状态*/

    private Integer status;

    /*** 创建时间*/
    @JsonFormat(pattern  = "yyyy-MM-dd")
    private Date createTime;

    /*** 修改时间*/
    @JsonFormat(pattern  = "yyyy-MM-dd")
    private Date updateTime;

    /**所属用户*/
    private String username;
}
