package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import com.itheima.utils.TransactionManager;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao ;
    //private TransactionManager tsManager;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAll() {
        return null;
    }

    public void updateAccount(Account account) {

    }

    public void insertAccount(Account account) {

    }

    public void deleteAccount(Integer id) {

    }

    public void transferTest(String sourceName, String targetName, Float money) {
        Account source = accountDao.findAccountByName(sourceName);
        Account target = accountDao.findAccountByName(targetName);
        //获取了两个账户的信息
        //进行转钱操作
        source.setMoney(source.getMoney()-money);
        target.setMoney(target.getMoney()+money);
        accountDao.updateAccount(source);
        accountDao.updateAccount(target);

    }
}
