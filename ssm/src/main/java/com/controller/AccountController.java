package com.controller;

import com.domain.Account;
import com.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    /**
    *@Description: ssm框架整合思路
     *  首先搭建spring的环境（applicationContext.xml）、springMVC的配置环境（SpringMvc.xml和web.xml）
     *  然后使用spring去整合springMVC
     *      整合思路：controller层需要调用service层，而加载springMVC.xml只会把controller层加入ioc
     *              则需要将service层的对象加入ioc，那就需要加载spring配置文件
     *              项目启动，则读取web.xml，则加载前端控制器，并且加载springmvc.xml，则扫描controller相关注解，而spring配置文件未读取到
     *              所以需要在项目启动时把spring配置文件也加载了，在web.xml中配置监听器加载spring文件
     *   再使用spring整合mybatis
     *          service层需要调用dao层，则需要将dao的代理对象加入ioc容器中，则在springioc配置文件中配置sqlsession工厂
     *          然后工厂生产sqlsession，进而生成dao代理对象
     *          在spring配置文件中配置mybatis相关的东西
     *   整合好之后，增删改需要手动提交事务，所以需要配置spring的声明式事务

    *@Param:
    *@return:
    *@Author: Su
    *@date: 2020/8/20
    */
    @Autowired
    private IAccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        System.out.println("查询所有账户");
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        return "list";
    }

    @RequestMapping("/saveAccount")
    public void saveAccount(Account account) {
        System.out.println("保存该账户");
        accountService.saveAccount(account);

    }
}
