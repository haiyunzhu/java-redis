package serviceImpl;

import api.RedisCacheService;

/**
 * Created by zhuhaiyun on 2018/1/24.
 */
public class RedisCacheServiceImpl implements RedisCacheService{
    @Override
    public void putObject(String key, Object value, int expiration) {
        // zhuhaiyun-下午10:14 TODO: jedis创建收拢
    }
}
