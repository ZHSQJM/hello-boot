package com.zhs.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhs
 * @Title: SessionUser
 * @ProjectName hello-boot
 * @Description: TODO
 * @date 2019/10/29 19:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionUser {

    private String session_key;

    private String openid;
}
