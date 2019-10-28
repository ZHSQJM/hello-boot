package com.zhs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/25 10:04
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> implements Serializable {

    private int row;

    private T data;
}
