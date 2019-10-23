package com.zhs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 16:40
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @NotNull(message = "用户名不能为空")
    private String name;

    @NotNull(message = "描述不能为空")
    private String description;
}
