package com.general_return.common;

/**
 * @program: general_return
 * @description: 通用返回对象
 * @author: Su
 * @create: 2020-08-21 19:59
 **/
public class CommonResult<T> {
    //状态码
    private long code;
    //提示消息
    private String message;
    //返回数据 由于该属性类型多样，则用泛型
    private T data;

    //不向该继承树之外暴露构造方法
    protected CommonResult() {

    }
    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
    *@Description: 由于外部无法访问构造函数，则方法使用静态方法供外调用
    *@Param:  成功返回结果 只传返回数据时
    *@return:
    *@Author: Su
    *@date: 2020/8/21
    */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),
        data);
    }

    //成功返回结果 传数据和提示消息时
    public static <T> CommonResult<T> success(String message,T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
    *@Description: 失败返回结果  传状态码枚举项
    *@Param: 由于枚举类ResultCode实现了ICode接口，则根据多态，枚举项都是ICode类型
    *@return:
    *@Author: Su
    *@date: 2020/8/21
    */
    public static <T> CommonResult<T> failed(ICode errorCode){
        return new CommonResult<T>(errorCode.getCode(),errorCode.getMessage(),null);
    }

    //失败返回结果，传提示信息
    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(ResultCode.FAILED.getCode(),message,null);
    }

    //失败返回结果 传提示信息和返回数据
    public static <T> CommonResult<T> failed(String message, T data) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, data);
    }

    //默认失败返回结果 什么都不传时 调用传状态码枚举项中的默认失败项
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果 使用默认提示消息
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果 自定义提示消息
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 超时验证结果返回
     */
    public static <T> CommonResult<T> timeout(String message) {
        return new CommonResult<T>(ResultCode.TIMEOUT.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    //根据结果的boolean值，来决定返回结果
    public static CommonResult<? extends Object> parseResultToResponse(boolean result, String failedMsg, String successMsg){
        if (!result)
            return CommonResult.failed(failedMsg);
        else
            return CommonResult.success(successMsg,true);
    }




}
