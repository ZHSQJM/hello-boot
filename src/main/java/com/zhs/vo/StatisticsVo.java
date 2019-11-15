package com.zhs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: zhouhuasheng
 * @date: 2019/11/14 15:24
 * @Description:
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StatisticsVo {

    /**下载数*/
    private Integer download;

    /**资源数*/
    private Integer resourceId;

     /**积分*/
    private  Integer integral;
}
