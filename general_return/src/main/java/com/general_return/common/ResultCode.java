package com.general_return.common;

/**
 * @program: general_return
 * @description: 结果状态码以及对应提示信息
 * @author: Su
 * @create: 2020-08-21 20:06
 **/
public enum  ResultCode implements ICode{
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(404,"参数检验失败"),
    UNAUTHORIZED(401,"暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    TIMEOUT(408, "队列请求超时");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
