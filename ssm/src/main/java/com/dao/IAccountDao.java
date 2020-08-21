package com.dao;

import com.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountDao {
    //查询所有账户
    @Select("select * from accounts")
    List<Account> findAll();

    //保存账户
    @Insert("insert into accounts(accountId,userName,money) values(#{accountId},#{userName},#{money})")
    void saveAccount(Account account);
}
