package com.itheima.service.impl;

import com.itheima.service.AccountService;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service("accountService")

public class AccountServiceImpl implements AccountService {

    public void saveAccount() {
        System.out.println("保存账户..");
    }

    public int insertAccount() {
        System.out.println("插入账户信息");
        return 0;
    }

    public void updateAccount() {
        System.out.println("更新操作..");
    }
}
