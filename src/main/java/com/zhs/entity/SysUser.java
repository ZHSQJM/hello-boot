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
 * @date: 2019/10/15 15:02
 * @Description:
 * @version: 1.0
 */

@Entity
@Table(name = "tb_sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    /**主键*/
    @Id
    @Column(length = 32)
    private Long id;

    /**用户名*/
    @Column(nullable = false,unique = true,length = 32)
    private String userName;

    /**密码*/
    @Column(nullable = false,unique = true,length = 32)
    private String password;

    /**性别 0 男  1 女  2 未知*/
    @Column(length = 1)
    private Integer sex;

    /**账号状态  0 正常  1 异常  2 删除*/
    @Column(nullable = false,length = 1)
    private Integer status;

    /**创建时间*/
    @Column(nullable = false)
    private Date createTime;

    /**更新时间*/
    @Column(nullable = false)
    private Date updateTime;
}
