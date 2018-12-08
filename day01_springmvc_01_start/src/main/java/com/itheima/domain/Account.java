package com.itheima.domain;

import java.util.List;
import java.util.Map;

public class Account {
    private String username;
    private String password;
    private Float money;
    //private User user;
    private List<User> users;
    private Map<String,User> maps;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<String, User> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, User> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", users=" + users +
                ", maps=" + maps +
                '}';
    }
}
