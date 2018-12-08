package com.itheima.test;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.domain.Account;
import com.itheima.factory.BeanFactory;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringTest {


    @Test
    public void test01() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountDao accountDao = (AccountDaoImpl) applicationContext.getBean("accountDao");
        List<Account> all = accountDao.findAll();
        for (Account account : all) {
            System.out.println(account);
        }
    }

    @Test
    /*
     * 测试没有进行事务控制的转钱案例*/
    public void test02() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.transferTest("aaa","bbb",100f);
    }

    @Test
    /*
     * 测试进行事务控制的转钱案例*/
    public void test03() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService2 = (AccountService) applicationContext.getBean("accountService2");
        accountService2.transferTest("aaa","bbb",100f);

    }
    @Test
    /*
     * 动态代理*/
    public void test04() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("proxyAccountService");
        accountService.transferTest("aaa","bbb",100f);

    }
}
