package com.zhs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/12 19:18
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ExchangeRecordsVo {


    private Long id;



    /**资源名称*/
    private String name;

    private String Description;

    /**资源图片*/
    private String phothUrl;

    /**消耗积分*/
    private Integer integral;

    /**兑换时间*/
    @JsonFormat(pattern  = "yyyy-MM-dd")
    private Date createTime;

    /**状态*/
    private Integer status;
}
