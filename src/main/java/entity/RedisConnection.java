package entity;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 从DistributedRedisLockTest演化而来
 * Created by zhuhaiyun on 2018/1/28.
 */
public class RedisConnection {
    /**
     * jedis连接池配置信息
     */
    private JedisPoolConfig jedisPoolConfig;
    // zhuhaiyun-下午5:30 TODO: 连接数的配置如何动态设置
    /**
     * jedis连接池
     */
    private JedisPool jedisPool;
    private String host;
    private Integer port;

    /**
     * 建立连接
     */
    private void buildConnection() {
        jedisPool = new JedisPool(new JedisPoolConfig(),host,port);
    }

    public Jedis getJedis() {
        buildConnection();
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = "127.0.0.1";
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = 6379;
    }
}
