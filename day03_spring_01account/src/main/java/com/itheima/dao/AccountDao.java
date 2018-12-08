package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAll();

    void updateAccount(Account account);

    void insertAccount(Account account);

    void deleteAccount(Integer id);

    Account findAccountByName(String name);
}
