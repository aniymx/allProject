package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findAccountById(Integer id);
    void saveAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Integer id);

}
