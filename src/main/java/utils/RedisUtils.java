package utils;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * Created by zhuhaiyun on 2018/1/21.
 */
public class RedisUtils {
    /**
     * 加锁
     * @param jedis
     * @param key
     * @param value
     * @param seconds  超时时间
     * @return
     */
    public static Boolean lock(Jedis jedis,String key,String value,int seconds) {
        //原子操作:判断key是否存在,保存key-value,设置超时时间
        String result = jedis.set(key, value, "NX", "EX", seconds);
        return StringUtils.isNotBlank(result) && "OK".equals(result);
    }

    /**
     *
     * @param jedis
     * @param key
     * @param value
     * @return
     */
    public static Boolean unlock(Jedis jedis,String key,String value) {
        //释放锁
        if (value.equals(jedis.get(key))) {
            return jedis.del(key) == 1;
        }
        return false;
    }

    public static void unlockByLua(Jedis jedis,String key,String value) {
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return " +
                "redis.call(\"del\",KEYS[1]) else return 0 end";
//        long result = (long)jedis.eval(script, 1, key, value);
        return;
    }
}
