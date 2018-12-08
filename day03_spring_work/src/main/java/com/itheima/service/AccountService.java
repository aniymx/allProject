package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    void updateAccount(Account account);
    void insertAccount(Account account);
    void deleteAccount(Integer id);
    void transferTest(String sourceName, String targetName, Float money);
}
