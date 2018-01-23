import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import utils.RedisUtils;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;


/**
 * Created by zhuhaiyun on 2018/1/21.
 */
public class DistributedRedisLockTest {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(10);
        //设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(18);
        //设置最大连接数
        jedisPoolConfig.setMaxTotal(150);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, 6379);
        //防止死锁
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
//            UUID uuid = UUID.randomUUID();
            new Thread(() -> {
                String uuid = UUID.randomUUID().toString();
                String key = "name";
                Jedis jedis = jedisPool.getResource();
                String value = "helloRedis";
                Boolean lock = RedisUtils.lock(jedis, key, value, 1);
                try{
                    if (lock) {
                        System.out.println(Thread.currentThread().getName()+"lock key = "+ key + " success!");
//                        Thread.sleep(200);
                    } else {
                        System.out.println(Thread.currentThread().getName() +"lock key = " + key + " fail!");
                    }
                }catch (Exception e) {

                }finally {
                    RedisUtils.unlock(jedis,key,value);
                    countDownLatch.countDown();
                }

            }).start();
        }
        countDownLatch.await();
        //多线程执行完之后执行主线程后面的逻辑
        System.out.println("--------hello-------");
    }
}
