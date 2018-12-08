package com.itheima.factory;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;
import com.itheima.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
    private AccountService accountService;
    private TransactionManager tsManager;

    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }

    public AccountService getAccountService() {
        return (AccountService) Proxy.newProxyInstance(
                AccountServiceImpl.class.getClassLoader(),
                AccountServiceImpl.class.getInterfaces(), new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                            try {
                                tsManager.beginTranscation();
                                rtValue = method.invoke(accountService, args);
                                System.out.println("动态代理执行");
                                tsManager.commit();
                                return rtValue;
                            } catch (Exception e) {
                                tsManager.rollback();
                                throw new RuntimeException(e);
                            } finally {
                                tsManager.release();
                            }
                    }
                });
    }

}
