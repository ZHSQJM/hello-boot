package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/17 15:37
 * @Description:
 * @version: 1.0
 */

@Entity
@Table(name = "tb_sys_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission {

    /**主键*/
    @Id
    @Column(length = 32)
    private Long id;

    /**权限名称*/
    private String name;

    /**地址*/
    private String url;

    /**上级ID*/
    private int pid;

    /**描述*/
    private String description;

    /**类型* 0 菜单  1接口地址  2 外链*/
    private Integer type;

    /**创建时间*/
    private Date createTime;

    /**更新时间时间*/
    private Date updateTime;

    /**懒加载 快速查询 不会查询role表*/
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permission",
            joinColumns = {@JoinColumn(name = "permission_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<SysRole> roles;

}
