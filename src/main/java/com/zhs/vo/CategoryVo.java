package com.zhs.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zhs.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 12:13
 * @Description:
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo implements Serializable {


    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private String icon;

    private Integer badge;

    private String color;

    private String description;
}
