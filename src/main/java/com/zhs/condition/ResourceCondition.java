package com.zhs.condition;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @project: hello-boot
 * @author: zhs
 * @date: 2019/11/10 14:08
 * @package: com.zhs.condition
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceCondition {

    /**资源名称 敬请期待阿西吧*/
    private String name;

    /**所属者*/
    private String openId;

    /**类型*/
    @JsonSerialize(using= ToStringSerializer.class)
    private Long categoryType;


    /*** 所属状态*/
    private Integer status;
}
