import api.RedisCacheService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhuhaiyun on 2018/1/23.
 */
public class RedisCacheTest {
    // zhuhaiyun-下午5:40 TODO: jedis cache test
    @Autowired
    private RedisCacheService redisCacheService;

    @Test
    public void testCache() {
        String key = "name";
        String value = "kivi";
        redisCacheService.putObject(key,value,1);
        // zhuhaiyun-下午8:39 TODO: 将键值放入缓存中
    }

}
