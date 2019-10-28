package com.zhs.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhs
 * @Title: UserCondition
 * @ProjectName hello-boot
 * @Description: TODO
 * @date 2019/10/15 20:06
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCondition {

    private String name;


    private Integer status;
}
