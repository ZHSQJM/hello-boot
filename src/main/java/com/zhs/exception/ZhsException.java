package com.zhs.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 16:19
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZhsException extends RuntimeException {

    private String msg;


}
