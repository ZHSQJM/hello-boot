package com.zhs.utils;

import com.zhs.enums.ResultCode;
import com.zhs.enums.ResultEnum;
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
public class Result<T> implements Serializable {

    /**状态码*/
    private Integer code;

    /**返回信息*/
    private String msg;

    /**返回数据*/
    private T data;


    public void setResultCode(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    /**
     * 成功  不返回数据直接返回成功信息
     */
    public static  Result success(){
        Result result = new Result();
        result.setResultCode(ResultEnum.SUCCESS);
        return result;
    }
    /**
     *   成功  并且加上返回数据
     */
    public static  Result success(Object data){
        Result result = new Result();
        result.setResultCode(ResultEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 成功  自定义成功返回状态 加上数据
     * @param resultEnum
     * @param data
     * @return
     */
    public static Result success(ResultEnum resultEnum,Object data){
        Result result = new Result();
        result.setResultCode(resultEnum);
        result.setData(data);
        return result;
    }


    public static Result failure(ResultEnum resultEnum){
        Result result = new Result();
        result.setResultCode(resultEnum);
        return result;
    }

    public static Result failure(ResultEnum resultEnum,Object data){
        Result result = new Result();
        result.setResultCode(resultEnum);
        result.setData(data);
        return result;
    }

    public static Result failure(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
