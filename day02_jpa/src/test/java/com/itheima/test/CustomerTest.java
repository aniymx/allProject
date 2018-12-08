package com.itheima.test;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class CustomerTest {

    @Autowired
    private CustomerDao customerDao;
    @Test
    public void testFindOne(){
        Customer one = customerDao.findOne(2l);
        System.out.println(one);
    }
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustName("zhangsan");
        customerDao.save(customer);
    }
    @Test
    public void testDelete(){
        customerDao.delete(1l);
    }
//count
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }
//判断id为3的是否存在
    @Test
    public void testExits(){
        boolean exists = customerDao.exists(3l);
        System.out.println(exists);
    }
//getOne延迟
    @Test
    @Transactional
    public void getOne(){
        Customer customer = customerDao.getOne(1l);
        System.out.println(1);
        System.out.println(customer);
    }







}
