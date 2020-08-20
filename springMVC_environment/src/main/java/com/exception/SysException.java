package com.exception;

/**
 * @program: springMVC_environment
 * @description: 系统异常类
 * @author: Su
 * @create: 2020-08-20 11:38
 **/
public class SysException extends RuntimeException {
    private String msg;

    public SysException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
