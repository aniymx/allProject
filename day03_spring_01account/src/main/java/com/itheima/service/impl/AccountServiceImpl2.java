package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import com.itheima.utils.ConnectionUtils;
import com.itheima.utils.TransactionManager;

import java.util.List;

public class AccountServiceImpl2 implements AccountService {
    private AccountDao accountDao;
    private TransactionManager tsManager;

    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }

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

    /*
     * 使用事务控制转账案例
     * 由于转账案例的特殊性,所以需要保持连接的一致性,在整个过程中都需要使用一个连接进行操作
     * 所以就不能使用queryrunner进行连接的维护,需要ThreadLocal进行连接的存取
     * 创建工具类,专门用来获取与释放链接
     *
     * */
    public void transferTest(String sourceName, String targetName, Float money) {
        try {
            //tsManager.beginTranscation();
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            //获取了两个账户的信息
            //进行转钱操作

            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);
            accountDao.updateAccount(source);
            int i = 1/0;
            accountDao.updateAccount(target);
         //   tsManager.commit();
        } catch (Exception e) {
           // tsManager.rollback();
            e.printStackTrace();
        } finally {

         //   tsManager.release();
        }

    }
}
