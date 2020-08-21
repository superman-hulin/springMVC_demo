package com.service;

import com.domain.Account;

import java.util.List;

public interface IAccountService {
    //查询所有账户
    List<Account> findAll();

    //保存账户
    void saveAccount(Account account);
}
