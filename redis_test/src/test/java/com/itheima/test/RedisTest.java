package com.itheima.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisTest {
    @Test
    public void test01() throws IOException {
        Jedis jedis = new Jedis("localhost", 6379);
        //jedis.set("name","zhangsan");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
        JedisPoolConfig config = new JedisPoolConfig();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("");
        Properties properties = new Properties();
        properties.load(is);
        config.setMaxIdle(Integer.parseInt(properties.getProperty("")));
        JedisPool jedisPool = new JedisPool(config, "", 6379);
        Jedis jedis1 = jedisPool.getResource();

        if (jedis.get("")!=null){

        }
    }
}
