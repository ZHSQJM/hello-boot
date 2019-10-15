package com.zhs;

import com.zhs.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:40
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    /**状态码*/
    private Integer code;

    /**返回信息*/
    private String msg;

    /**返回数据*/
    private Object data;


    public void setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

    /**
     * 成功  不返回数据直接返回成功信息
     */
    public static  Result success(){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }
    /**
     *   成功  并且加上返回数据
     */
    public static  Result success(Object data){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 成功  自定义成功返回状态 加上数据
     * @param resultCode
     * @param data
     * @return
     */
    public static Result success(ResultCode resultCode,Object data){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }


    public static Result failure(ResultCode resultCode){
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode,Object data){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

}
