import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import utils.RedisUtils;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static javafx.scene.input.KeyCode.R;

/**
 * Created by zhuhaiyun on 2018/1/21.
 */
public class DistributedRedisLockTest {
    public static void main(String[] args) throws Exception{
        String host = "172.0.0.1";
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(10);
        //设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(18);
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(150);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,6379);
        //// zhuhaiyun-下午10:04 TODO: 了解CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
//            UUID uuid = UUID.randomUUID();
            new Thread(()->{
                String uuid = UUID.randomUUID().toString();
                String key = "age";
                Jedis jedis = jedisPool.getResource();
                try {
                    boolean result = false;
                    do {
                        RedisUtils.lock(jedis, key, uuid, 30);
                    } while (result == false);
                    //// zhuhaiyun-下午10:25 TODO: 含义是什么
                    int value = Integer.valueOf(jedis.get("value"));
                    jedis.set("value", ++value + "");
                }finally {
                    jedis.close();
                    RedisUtils.unlock(jedis,key,uuid);
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        Jedis jedis = new Jedis(host,6379);
        int value = Integer.parseInt(jedis.get("value"));
        System.out.println("value="+value);
    }
}
