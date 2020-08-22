package com.general_return.exception;

import com.general_return.common.ResultCode;

/**
 * @program: general_return
 * @description: 自定义用户异常类
 * @author: Su
 * @create: 2020-08-21 20:50
 **/
public class UserException extends RuntimeException {
    private ResultCode code;
    private String message;

    public UserException(String msg, ResultCode code) {
        super(msg);
        this.message = msg;
        this.code = code;
    }

    public UserException(String msg) {
        super(msg);
        this.message = msg;
        this.code = ResultCode.FAILED;
    }
}
