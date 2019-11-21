package com.zhs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 16:41
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WeixinUserDto {


    private String code;
    /**微信用户的openId*/
    private String openId;

    /**头像地址*/
    private String avatarUrl;

    private Integer gender;

    private String nickName;

    private String province;



}
