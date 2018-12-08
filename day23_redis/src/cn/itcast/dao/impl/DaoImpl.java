package cn.itcast.dao.impl;

import cn.itcast.dao.Dao;
import cn.itcast.domain.City;
import cn.itcast.domain.Province;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DaoImpl implements Dao {
private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Province> findProvince() {
        String sql = "select * from province";
        List<Province> list = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
       // System.out.println(list);
        return list;
    }

    @Override
    public List<City> findCity(int id) {
        String sql = "select * from city where pid = ?";
        List<City> cityList = template.query(sql, new BeanPropertyRowMapper<City>(City.class), id);

        System.out.println(cityList);

        return cityList;
    }

}
