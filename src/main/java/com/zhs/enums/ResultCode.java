package com.zhs.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:34
 * @Description: API 统一返回状态码
 * @version: 1.0
 */

@Getter
@AllArgsConstructor
public enum ResultCode {

    /*** 成功状态*/
    SUCCESS(1, "成功"),
    /*** 返回失败状态*/
   FAILURE(0, "失败");


    private Integer code;

    private String message;

    public Integer code(){
        return this.code;
    }

    public String message(){
        return  this.message;
    }
    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static  Integer getCode(String name){
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    /**
     *    SAVE_SUCCESS(1,"保存成功"),
     *     SAVE_FAILURE(0,"保存失败"),
     *     DELETE_SUCCESS(1,"删除成功"),
     *     DELETE_FAILURE(0, "删除失败"),
     *     UPDATE_SUCCESS(1,"更新成功"),
     *     UPDATE_FAILURE(0,"更新失败"),
     *     FIND_SUCCESS(1, "查询成功"),
     *     FIND_FAILURE(0, "查询失败"),
     *     SYSYTEM_ERROR(0, "系统错误");
     */
}