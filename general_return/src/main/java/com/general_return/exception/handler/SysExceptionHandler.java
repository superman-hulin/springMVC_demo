package com.general_return.exception.handler;

import com.general_return.common.CommonResult;
import com.general_return.exception.SystemException;
import com.general_return.exception.UserException;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: general_return
 * @description: springboot统一异常处理类
 * 无论是dao层还是service层出现异常，只要该异常没有处理，都将会统一异常处理类拦截
 * 该类会监听controller调用链中是否出现了未被处理的异常
 * @author: Su
 * @create: 2020-08-21 20:38
 **/
@RestControllerAdvice
public class SysExceptionHandler {

    @ExceptionHandler(value = SystemException.class) //指定拦截发生的SystemException异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult error(SystemException e, HttpServletResponse response){
        e.printStackTrace(); //打印异常
        //首先自定义提示消息，将本来的异常消息e.getMessage()作为data也返回
        return CommonResult.failed("工程模块内部错误", e.getMessage());
    }

    @ExceptionHandler(value = UserException.class)//指定拦截发生的UserException异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult error(UserException e, HttpServletResponse response){
        e.printStackTrace();
        return CommonResult.failed("用户模块内部错误", e.getMessage());
    }

    /**
     * 服务器内部错误
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class}) //拦截运行时异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult error(Exception e, HttpServletResponse response){
        e.printStackTrace();
        return CommonResult.failed("服务器内部错误", e.getMessage());
    }

    //AccessDeniedException为spring security中拒绝访问的异常（即可能未登录等原因）
//    @ExceptionHandler(value = AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public CommonResult error(AccessDeniedException e, HttpServletResponse response){
//        e.printStackTrace();
//        return CommonResult.forbidden(e.getMessage());
//    }


}
