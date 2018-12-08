package com.itheima.test;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;
    @Test
    public void findByName(){
        Customer customer = customerDao.findJpql("zhangsan");
        System.out.println(customer);
    }
    @Test
    public void findByNameAndId(){
        Customer zhangsan = customerDao.findByNameAndId2( 2l,"zhangsan");
        System.out.println(zhangsan);
    }

    //update
    @Test
    @Transactional
    @Rollback(value = false)
    public void updateByNameAndId(){
        customerDao.updateByNameAndId(2l,"lisi");
    }




}
