package com.general_return.common;

/**
*@Description: 状态码规范
*@Param:
*@return:
*@Author: Su
*@date: 2020/8/21
*/
public interface ICode {
    //获取状态码
    long getCode();
    //获取提示信息
    String getMessage();
}
