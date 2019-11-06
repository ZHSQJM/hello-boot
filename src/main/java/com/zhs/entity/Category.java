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
 * @date: 2019/10/28 8:49
 * @Description: ；类别
 * @version: 1.0
 */
@Data
@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Category {

    /**主键**/
    @Id
    @Column(length = 32)
    private Long id;

    /**名称**/
    @Column(length = 32)
    private String name;

    /**描述**/
    @Column(length = 255)
    private String description;

    /**图标*/
    @Column(length = 255)
    private String url;

    private Integer status;

    /**创建时间**/
    private Date createTime;

    /**更新时间**/
    private Date updateTime;

}
