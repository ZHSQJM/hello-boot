package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/11/15 22:39
 * @package: com.zhs.entity
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class SignRecords implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String openid;


    private Date date;

    private String dateTime;

}
