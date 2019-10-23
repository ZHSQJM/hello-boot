package com.zhs.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 16:50
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo {

    /**主键*/
    private Long id;

    /**用户名*/
    private String username;

    /**性别 0 男  1 女  2 未知*/
    private Integer sex;

    /**账号状态  0 正常  1 异常  2 删除*/
    private Integer status;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;


    private String roleName;
}
