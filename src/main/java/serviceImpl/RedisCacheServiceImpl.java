package serviceImpl;

import api.RedisCacheService;
import entity.RedisConnection;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * Created by zhuhaiyun on 2018/1/24.
 */
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private RedisConnection redisConnection;

    @Override
    public void putObject(String key, Object value, int expiration) {
        Jedis jedis = redisConnection.getJedis();
        if (expiration > 0) {
            jedis.setex(key, expiration, value.toString());
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
}
