package com.general_return.exception;

import com.general_return.common.ResultCode;

/**
 * @program: general_return
 * @description: 系统自定义异常类
 * @author: Su
 * @create: 2020-08-21 20:33
 **/
public class SystemException extends RuntimeException{
    //错误码
    private ResultCode code;
    //提示消息
    private String message;


    public SystemException(ResultCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    //沿用默认失败返回的状态码
    public SystemException(String message) {
        super(message);
        this.message = message;
        this.code=ResultCode.FAILED;
    }
}
