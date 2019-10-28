package com.zhs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 16:41
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeixinUserDto {


    /**微信用户的openId*/
    private String openId;

    /**头像地址*/
    private String avarlUrl;

}