package com.zhs.exception;

import com.aliyun.oss.OSSException;
import com.zhs.enums.ResultEnum;
import com.zhs.utils.Result;
import com.zhs.enums.ResultCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/15 15:32
 * @Description:
 * @version: 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * MethodArgumentNotValidException异常是@RequestBody 和@Valid校验参数不合格时抛的异常，
     * BindException异常是@Valid校验参数不合格时抛的异常，所以需要对这两种异常进行处理。
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result bindExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException ex){
            BindingResult bindingResult = ex.getBindingResult();
            List<ObjectError> errors = bindingResult.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.failure(ResultEnum.USER_NOT_FOUNT,msg);
    }


    /**
     * 捕获系统错误
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest request, Exception ex){
        ex.printStackTrace();
        return Result.failure(ResultEnum.FAIL);
    }

    /**
     * 捕获自定义异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ZhsException.class)
    public Result zhsExceptionHandler(ZhsException ex){
        return Result.failure(ex.getCode(),ex.getMessage());
    }

//    @ExceptionHandler(value = OSSException.class)
//    public Result OSSExceptionHandler(OSSException ex){
//        return Result.failure(ResultCode.FAILURE,ex.getMessage());
//    }

}
