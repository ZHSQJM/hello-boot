package com.zhs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 9:38
 * @Description: 资源
 * @version: 1.0
 */
@Table(name = "resource")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Resource implements Serializable {


    /*** 主键*/
    @Id
    @Column(length = 32)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /*** 资源名称*/
    @Column(length = 255)
    private String name;

    /**封面地址*/

    private String phothUrl;

    /*** 所属用户*/
    @Column(length = 255)
    private String openId;

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
    /**0审核成功 1 未审核  2 审核成功 3 审核失败*/
    @Column(length = 32)
    private Integer status;

    /*** 创建时间*/
    @Column(length = 255)
    @JsonFormat(pattern  = "yyyy-MM-dd")
    private Date createTime;

    /*** 修改时间*/
    @Column(length = 255)
    private Date updateTime;

    /**兑换数*/
    private Integer records;

}
