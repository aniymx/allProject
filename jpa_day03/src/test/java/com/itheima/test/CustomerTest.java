package com.itheima.test;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class CustomerTest {
    @Autowired
    private CustomerDao customerDao;

    //精准匹配
    @Test
    public void test() {
        Specification<Customer> customerSpecifications = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = criteriaBuilder.equal(custName, "lisi");
                return predicate;
            }
        };
        Customer one = customerDao.findOne(customerSpecifications);
        System.out.println(one);

    }


    @Test
    public void test02() {
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path<Object> custName = root.get("custName");
                Predicate lisi = criteriaBuilder.equal(custName, "lisi");
                return lisi;
            }
        };
        Customer one = customerDao.findOne(specification);
        System.out.println(one);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testInsert() {
        Customer customer = new Customer();
        customer.setCustName("mengxuchao");
        customer.setCustPhone("123");
        customerDao.save(customer);
    }


    //多条件查询
    @Test
    public void testFind() {
        Specification specification = new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path name = root.get("custName");
                Path custPhone = root.get("custPhone");
                Predicate pname = criteriaBuilder.equal(name, "yaya");
                Predicate pnum = criteriaBuilder.equal(custPhone, "123");
                Predicate predicate = criteriaBuilder.and(pname, pnum);
                return predicate;
            }
        };
        Customer one = customerDao.findOne(specification);
        System.out.println(one);
    }


    //模糊查询
    //根据客户名称的模糊查询
//equal直接得到path可以进行比较,
    //gt,lt,ge,le,like: 得到path对象,根据path指定比较的类型,再比较
@Test
    public void  testFindAllLike(){
        Specification<Customer> customerSpecification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate meng = criteriaBuilder.like(custName.as(String.class), "meng%");
                return meng;
            }
        };
        List<Customer> all = customerDao.findAll(customerSpecification);
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }


    //排序

    @Test
    public void  testFindAll(){
        Specification<Customer> customerSpecification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate meng = criteriaBuilder.like(custName.as(String.class), "meng%");
                return meng;
            }
        };
       //添加排序
        //创建排序对象,创建调用够着方法进行实例化
        //第一个顺序,第二个排序的属性名
        Sort custId = new Sort(Sort.Direction.ASC, "custId");
        List<Customer> all = customerDao.findAll(customerSpecification, custId);
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

/*
* 分页查询
* Pageable:分页参数  查询的页码,查询的条数
* findAll
* 返回:Page(Spring封装好的对象)
*
* */
    @Test
    public void testPage(){
Specification specification = null;
//pageRequest对象是pageable的接口

        Pageable pageable = new PageRequest(0,2);
        Page<Customer> page = customerDao.findAll(null, pageable);
        List<Customer> content = page.getContent();
        for (Customer customer : content) {
            System.out.println("数据"+customer);
        }
        System.out.println(page.getTotalElements()+"条数");
        System.out.println(page.getTotalPages()+"页数");


    }

//一 的一方是主表       多的一方是从表  //   中间表至少有两个主键
    //


















}
