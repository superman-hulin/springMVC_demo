package com.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: springMVC_environment
 * @description: 自定义拦截器
 * @author: Su
 * @create: 2020-08-20 16:06
 **/
public class MyInterceptor implements HandlerInterceptor {
    /**
    *@Description: 预处理 controller方法执行之前 可以判断用户是否登录
    *@Param:  return true 表示放行
     *               false  不放行
    *@return: 
    *@Author: Su
    *@date: 2020/8/20
    */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("放行了");
        return true;
    }

    //后处理
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("后处理");
    }

    //页面执行后 再执行该方法 一般释放资源
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
