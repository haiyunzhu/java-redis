package com.redis.test.serviceImpl;

import com.redis.test.api.RedisCacheService;
import com.redis.test.entity.RedisConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Created by zhuhaiyun on 2018/1/24.
 */
@Component
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private RedisConnection redisConnection;

    @Override
    public void putObject(String key, int expiration, String... value) {
        Jedis jedis = redisConnection.getJedis();
        String[] values = value;
        if (expiration > 0) {
            //将value关联到key,并设置key的生存时间
            jedis.setex(key, expiration, values[0]);
        }
    }



    @Override
    public Object pullObject(String key) {
        Jedis jedis = redisConnection.getJedis();
        return jedis.get(key);
    }

    @Override
    public boolean expire(String key, int expireSecond) {
        Jedis jedis = redisConnection.getJedis();
        return jedis.expire(key, expireSecond) == 1 ? true : false;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = redisConnection.getJedis();
        return jedis.ttl(key);
    }

    @Override
    public boolean delObject(String key) {
        Jedis jedis = redisConnection.getJedis();
        return jedis.del(key) > 0;
    }

    @Override
    public void putHash(String key, String field, String value) {
        Jedis jedis = redisConnection.getJedis();
        jedis.hset(key,field,value);
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = redisConnection.getJedis();
        return jedis.hget(key, field);
    }
}
