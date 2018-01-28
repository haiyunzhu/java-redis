package api;

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
    void putObject(String key,Object value,int expiration);

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
}
