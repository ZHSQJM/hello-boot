package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 16:35
 * @Description: 兑换记录
 * @version: 1.0
 */

@Table(name = "exchange_records")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ExchangeRecords {

    /**主键*/
    @Id
    private Long id;

    /**用户ID*/
    private String userId;

    /**资源ID*/
    private Long resourceId;

    /**消耗积分*/
    private Integer integral;

    /**创建时间*/
    private Date createTime;

    /**状态*/
    private Integer status;


}
