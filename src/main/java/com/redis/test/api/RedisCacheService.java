package com.redis.test.api;

/**
 * redis缓存服务接口
 * Created by zhuhaiyun on 2018/1/24.
 */
public interface RedisCacheService {

    /**放入缓存中,并设置过期时间
     * fang
     * @param key
     * @param value
     * @param expiration
     */
    void putObject(String key,int expiration,String... value);



    /**
     * 从缓存中获取对象
     * @param key
     * @return
     */
    Object pullObject(String key);

    /**
     * 给缓存对象设置过期秒数
     * @param key
     * @param expireSecond
     * @return
     */
    boolean expire(String key,int expireSecond);

    /**
     * 获取缓存对象设置的过期秒数
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 从缓存中删除对象
     * @param key
     * @return
     */
    boolean delObject(String key);

    void putHash(String key,String field,String value);

    /**
     * 根据key获取hash值
     * @param key
     * @param field
     * @return
     */
    String hget(String key,String field);
}
