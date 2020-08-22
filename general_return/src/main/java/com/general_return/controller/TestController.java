package com.general_return.controller;

import com.general_return.common.CommonResult;
import com.general_return.exception.SystemException;
import com.general_return.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: general_return
 * @description: 测试通用返回的controller
 * @author: Su
 * @create: 2020-08-22 07:55
 **/
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("/test")
    public CommonResult test(){
        testService.tset();
        //由于service层出现异常，并未处理，则被统一异常处理类拦截，返回CommonResult.failed
        return CommonResult.success("成功");
    }
}
