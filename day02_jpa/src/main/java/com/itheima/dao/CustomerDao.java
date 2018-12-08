package com.itheima.dao;

import com.itheima.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    /*
     * 案例,根据客户名称查询客户
     * jpql:from customer where cust = ?
     *
     * */
    @Query(value = "from Customer where custName = ?")
    public Customer findJpql(String custName);


    /*
    * 在?后没有添加索引的情况下,?的顺序必须要跟参数进行匹配
    * */
    @Query(value = "from Customer where custName = ? and custId = ?")
    Customer findByNameAndId(String name, Long id);

    /*
     * 在?后添加索引的,?的顺序必须要跟参数进行匹配,不同顺序使用参数进行控制
     * */
    @Query(value = "from Customer where custName = ?2 and custId = ?1")
    Customer findByNameAndId2(Long id,String name );

    /*更新
    *
    * sql:update cst_customer set cust_name = ? where cust_id = ?
    * jpql:update Customer set custName = ? where custId  = ?
    *
    *
    *
    * */

    @Query(value = "update Customer set custName = ?2 where custId  = ?1")
    @Modifying
    void updateByNameAndId(Long id,String name );


/*nativeQuery = true使用本地查询(sql)*/
    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Object[]> findSql();



}
