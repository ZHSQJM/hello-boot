package com.zhs.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhs.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/24 16:49
 * @Description:
 * @version: 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {




    /**主键*/
    private Long id;

    /**角色名称*/
    private String name;

    /**角色描述*/
    private String description;

    /**角色状态  0 正常  1 异常  2 删除*/
    private Integer status;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

}
