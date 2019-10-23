package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:02
 * @Description:
 * @version: 1.0
 */

@Entity
@Table(name = "tb_sys_user")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class SysUser implements UserDetails, Serializable {

    /**主键*/
    @Id
    @Column(length = 32)
    private Long id;

    /**用户名*/
    @Column(nullable = false,unique = true,length = 32)
    private String username;

    /**密码*/
    @Column(nullable = false,length = 32)
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


    /**角色关联表*/
    /**
     * all: 所有情况下均进行关联操作，即save-update和delete。
     * none: 所有情况下均不进行关联操作。这是默认值。
     * save-update: 在执行save/update/saveOrUpdate时进行关联操作。
     * delete: 在执行delete 时进行关联操作。
     * all-delete-orphan: 当一个节点在对象图中成为孤儿节点时，删除该节点。
     * 比如在一个一对多的关系中，Student包含多个book，当在对象关系中删除一个book时，
     * 此book即成为孤儿节点。
     */
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<SysRole> authoristies;



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoristies;
    }

    public void setAuthoristies(List<SysRole> authoristies) {
        this.authoristies = authoristies;
    }
}
