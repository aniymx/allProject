package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest {
    @Autowired
    private AccountService accountService;
    @Test
    public void  testFindAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
         accountService = ac.getBean("accountService", AccountServiceImpl.class);
        List<Account> all = this.accountService.findAll();
        for (Account account : all) {
            System.out.println(account);
        }
    }
}
