import com.redis.test.api.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * Created by zhuhaiyun on 2018/1/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath*:/config/spring/local/appcontext-*.xml"
        })
public class RedisCacheTest {
    @Autowired
    // zhuhaiyun-下午11:36 TODO: Autowired如何初始化  为什么没有调用set方法
    private RedisCacheService redisCacheService;

    /**
     * 测试基于k-value数据的缓存
     */
    @Test
    public void testCache() {
        String key = "name";
        String value = "kivi";
        //expiration超时时间设置为1s  测试过程中就失效了 建议时间设长点
        redisCacheService.putObject(key, 1000, value);
        // zhuhaiyun-下午8:39 TODO: 将键值放入缓存中
        String cacheValue = (String) redisCacheService.pullObject(key);
        System.out.println(value + "\n" + cacheValue);
    }

    /**
     * 测试基于hash数据的缓存
     */
    @Test
    public void testHashCache() {
        String key = "hash";
        //===
        String field1 = "sex";
        String value1 = "male";
        //===
        String field2 = "age";
        String value2 = "18";
//        String value01 = "female";
        redisCacheService.putHash(key, field1, value1);
        redisCacheService.putHash(key, field2, value2);
        //设置过期时间
        redisCacheService.expire(key, 1000);
        String hvalue1 = redisCacheService.hget(key, field1);
        System.out.println("value1:" + value1 + "\nhvalue:" + hvalue1);

    }

    /**
     * 测试删除缓存
     */
    @Test
    public void testDelCache() {
        boolean hash = redisCacheService.delObject("hash");
        System.out.println(hash);
    }


    @Test
    public void testTtlCahce() {
        Long name = redisCacheService.ttl("name");
        System.out.println("expire time:"+name);
    }

}
