package cn.itcast.service;

import cn.itcast.domain.City;
import cn.itcast.domain.Province;

import java.util.List;

public interface Service {
    List<Province> findProvince();
    String findProvinceJson();
    List<City> findCity(int id);
}
