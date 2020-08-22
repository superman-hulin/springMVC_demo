package com.general_return.service;

import com.general_return.exception.SystemException;
import org.springframework.stereotype.Service;

/**
 * @program: general_return
 * @description: 测试通用返回的业务层
 * @author: Su
 * @create: 2020-08-22 08:26
 **/
@Service
public class TestService {

    public String tset(){
        try {
            int i=10/0;
        }catch (Exception e){
            throw new SystemException("除0操作了");
        }
        return "1";
    }
}
