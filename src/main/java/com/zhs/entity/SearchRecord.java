package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @project: study-resource
 * @author: zhs
 * @date: 2019/11/24 15:11
 * @package: com.zhs.entity
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class SearchRecord {

    @Id
    private Long id;

    private  String openId;

    private String keyword;

    private Date createTime;

}
