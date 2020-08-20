package com.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springMVC_environment
 * @description: 自定义字符串转日期的转换器
 *     前端向后台传的数据都是string类型，springmvc会有各种类型转换器帮我们自动转换数据类型
 *     但是日期的转换仅支持该种  2020/08/18 字符串格式
 *     自定义 将2020-08-18转成日期
 * @author: Su
 * @create: 2020-08-18 09:42
 **/
public class StringToDateConverter implements Converter<String,Date> {

    @Override
    public Date convert(String s) {
        Date d=null;
        if(s==null){
            throw new RuntimeException("请您传入数据");
        }
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        try {
            d= df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
