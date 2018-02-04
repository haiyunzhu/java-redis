import com.redis.test.api.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhuhaiyun on 2018/1/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath*:/config/spring/local/appcontext-*.xml"
        })
public class RedisCacheTest {
    // zhuhaiyun-下午5:40 TODO: jedis cache test
    @Autowired
    // zhuhaiyun-下午11:36 TODO: Autowired如何初始化  为什么没有调用set方法
    private RedisCacheService redisCacheService;

    @Test
    public void testCache() {
        String key = "name";
        String value = "kivi";
        //expiration超时时间设置为1s  测试过程中就失效了 建议时间设长点
        redisCacheService.putObject(key,value,100);
        // zhuhaiyun-下午8:39 TODO: 将键值放入缓存中
        String cacheValue = (String)redisCacheService.pullObject(key);
        System.out.println(value + "" + cacheValue);
    }

}
