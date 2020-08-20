package com.utils;

import com.exception.SysException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: springMVC_environment
 * @description: 异常处理器
 * @author: Su
 * @create: 2020-08-20 11:47
 **/
public class SysExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获取到异常对象
        SysException sysException=null;
        if(e instanceof SysException){
            sysException=(SysException)e;
        }
        else {
            sysException=new SysException("服务器错误");
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("errorMsg",sysException.getMsg());
        mv.setViewName("error");
        return mv;
    }
}
