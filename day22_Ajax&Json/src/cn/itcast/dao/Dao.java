package cn.itcast.dao;

import cn.itcast.domain.Person;
import cn.itcast.domain.User;

import java.util.List;

public interface Dao {
    User findUser(String username);

    List<String> search(String value);
}
