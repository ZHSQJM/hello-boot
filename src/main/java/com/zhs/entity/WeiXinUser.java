package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 16:26
 * @Description:
 * @version: 1.0
 */
@Entity
@Table(name = "weixin_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WeiXinUser {

    /**微信用户的openId*/
    @Id
    @Column(length = 32)
    private String openId;

    /**头像地址*/
    private String avarlUrl;

    /**第一次登陆时间*/
    private Date createTime;

    /**最近登陆时间*/
    private Date recentlyTime;

    /**个人积分*/
    private Integer integral;
}
