package cn.itcast.service.impl;

import cn.itcast.dao.Dao;
import cn.itcast.dao.impl.DaoImpl;
import cn.itcast.domain.City;
import cn.itcast.domain.Province;
import cn.itcast.service.Service;
import cn.itcast.utils.JedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ServiceImpl implements Service {
    Dao dao = new DaoImpl();

    @Override
    public List<Province> findProvince() {
        List<Province> province = dao.findProvince();
        return province;
    }
    @Override
    public String findProvinceJson(){

        Jedis jedis = JedisUtils.getJedis();
        String province_json = jedis.get("province");
        if (province_json==null || province_json.length()==0){
            //empty data
            System.out.println("没有数据,查询数据库");
            List<Province> province = dao.findProvince();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                province_json = objectMapper.writeValueAsString(province);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("province",province_json);
            jedis.close();
            return province_json;
        }else {
            System.out.println("有数据.查询缓存");
            return province_json;
        }
    }

    @Override
    public List<City> findCity(int id) {
        List<City> city = dao.findCity(id);
        return city;
    }

}
