package com.zhs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
public class SysRole implements GrantedAuthority {



    /**主键*/
    @Id
    @Column(length = 32)
    private Long id;

    /**角色名称*/
    @Column(nullable = false,unique = true,length = 255)
    private String name;

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

    /**懒加载 不会查询role表*/
    @ManyToMany(mappedBy = "authoristies",fetch = FetchType.LAZY)

    /**在查询的时候会包循环 错误 栈溢出报错
     * https://blog.csdn.net/weixin_39841589/article/details/83409835*/
    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    private List<SysUser> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
