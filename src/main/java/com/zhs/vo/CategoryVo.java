package com.zhs.vo;

import com.zhs.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CategoryVo {


    private Long id;

    private String name;

    private List<Resource> list;
}
