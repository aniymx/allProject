package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findCategory() {
        //查询数据库
        // List<Category> list = categoryDao.findCategory();
//查询redis,如果没有就查询数据库
        Jedis jedis = JedisUtil.getJedis();
        // Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> list = null;
        if (categorys == null || categorys.size() == 0) {
            //没有数据,从数据库中取数据
            //  System.out.println("从数据库中进行取数据");
            list = categoryDao.findCategory();
            //并且吧数据存进redis
            for (Category category : list) {
                jedis.zadd("category", category.getCid(), category.getCname());
            }
            jedis.close();
        } else {
            //  System.out.println("从redis中进行取 数据");
            list = new ArrayList<Category>();

            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                list.add(category);
            }
            jedis.close();
        }


        return list;
    }
}
