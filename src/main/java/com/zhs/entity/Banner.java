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
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/10/30 15:59
 * @package: com.zhs.entity
 * @description:
 */

@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
public class Banner {

    @Id
    private Long id;

    @Column(length = 10000)
    private String url;

    private Date createTime;

    private Date updateTime;

    private Integer status;


}
