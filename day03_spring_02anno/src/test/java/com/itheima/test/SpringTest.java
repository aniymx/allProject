package com.itheima.test;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringTest {
    @Autowired
    private AccountService as = null ;
    @Test
    public void test01(){
       // ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //AccountService accountService = (AccountService) as.getBean("accountService");
        as.saveAccount();
    }
   /* public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) ac.getBean("accountService");
        accountService.saveAccount();
    }*/
}
