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
}
