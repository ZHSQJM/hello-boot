package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 16:40
 * @Description:
 * @version: 1.0
 */
@Entity
@Table(name = "tb_sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {

    /**主键*/
    @Id
    @Column(length = 32)
    private Long id;

    /**角色名称*/
    @Column(nullable = false,unique = true,length = 255)
    private String roleName;

    /**角色描述*/
    @Column(nullable = false,unique = true,length = 255)
    private String description;

    /**角色状态  0 正常  1 异常  2 删除*/
    @Column(nullable = false,length = 1)
    private Integer status;

    /**创建时间*/
    @Column(nullable = false)
    private Date createTime;

    /**更新时间*/
    @Column(nullable = false)
    private Date updateTime;
}
