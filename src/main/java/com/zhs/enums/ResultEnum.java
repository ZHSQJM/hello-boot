package com.zhs.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    //UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    FAIL(-1,"未知错误"),
    USER_NOT_FOUNT(10001, "用户不存在"),
    RESOURCE_NOT_FOUND(10002, "资源不存在"),
    USER_HAS_SOGN(10003,"用户已经签到"),
    INTEGRAL_INSUFFICIENT(10004,"积分不足,请及时登录领取积分"),
    CATEGORY_NOT_FOUND(10005," 没有该类目,请重新选择"),
    RESOURCE_BE_OVERDUE(10006," 资源过期了"),
    EXAMINE_NOT_PASS(10007,"审核未通过"),
    USER_HAS_EXIT(10008,"用户已存在"),
    PARAMETER_ERROR(10009,"参数异常")




    ;

    private Integer code;

    private String msg;


}
