package com.zhs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 10:41
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    /**主键**/
    private Long id;

    /**名称**/
    private String name;

    /**描述**/
    private String description;
}
