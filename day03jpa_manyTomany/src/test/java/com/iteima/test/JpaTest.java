package com.iteima.test;

import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class JpaTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;


    @Test
    @Transactional
    @Rollback(false)
    public void testInsert() {
        User user = new User();
        user.setUserName("meng");
        Role role = new Role();
        role.setRoleName("student");
        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);


    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDel() {
        userDao.delete(1l);
    }

}
