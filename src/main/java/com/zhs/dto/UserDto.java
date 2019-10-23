package com.zhs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:24
 * @Description:
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private  Long id;

    @NotNull(message = "用户名不能为空")
    private String username;

    @Length(min=8,message = "密码长度不能少于8")
    private String password;

    @NotNull(message = "性别不能为空")
    private Integer sex;

}
