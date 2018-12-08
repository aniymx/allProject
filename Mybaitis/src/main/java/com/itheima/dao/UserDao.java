package com.itheima.dao;

import com.itheima.domian.QueryVo;
import com.itheima.domian.User;

import java.util.List;

public interface UserDao {
    //查
    List<User> findAll();
    //增加
    void insertUser(User user);
    //删除
    void deleteUser(Integer id);
    //更新
    void updateUser(User user);
    int countUser();
    List<User> findByQueryVo(QueryVo queryVo);
}
