package com.itheima.test;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class JpaTest {

    @Autowired
    private CustomerDao customerDao;
    @Test
    public  void test01(){
        List<Customer> customerList = customerDao.findAll();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    @Test
    public void testfindOne(){
        Customer one = customerDao.findOne(2l);
        System.out.println(one);
    }

    //update
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustName("123");
        Customer save = customerDao.save(customer);
        System.out.println(save);
    }

    @Test
    public void testfind(){

        Customer save = customerDao.findByCustName("lisi");
        System.out.println(save);
    }




}
