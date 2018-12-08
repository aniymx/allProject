package cn.itcast.dao;

import cn.itcast.domain.City;
import cn.itcast.domain.Province;

import java.util.List;

public interface Dao {
    List<Province> findProvince();
    List<City> findCity(int id);

}
