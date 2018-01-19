import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhuhaiyun on 2018/1/19.
 */
public class TestRedis {
    private Jedis jedis;

    @Before
    public void setJedis() {
        jedis = new Jedis("127.0.0.1", 6379);
//        System.out.println("连接服务器成功");
        //去ping测试机所在的主机和端口
//        System.out.println("连接服务器成功"+jedis.ping());
    }

    /**
     * Redis操作字符串
     */
    @Test
    public void testString() {
        //添加数据
        jedis.set("name","kivi");
        System.out.println(jedis.get("name"));
        jedis.set("age","18");
        //删除某个键值对
//        jedis.del("name");
//        //设置多个键值对
        jedis.mset("name","helloRedis","age","18","email","123@qw.com");
        jedis.incr("age");
        System.out.println(jedis.get("name")+jedis.get("age"));

    }

    @Test
    public void testMap() {
        //添加数据
//        Map<String,String> map = Maps.newHashMap();
        Map<String,String> map = new HashMap<String, String>();
        map.put("qa","1");
        map.put("qa1","2");
        map.put("qa2","3");
        jedis.hmset("user",map);
        //hmget(String key, String... fields)
        System.out.println(jedis.hmget("user","qa","qa1"));
        //hdel(String key, String... fields)
        jedis.hdel("user","qa","qa1");
        //hlen(String key)
        //hexists(String key, String field)
        //exists(String key)
        System.out.println(jedis.exists("user"));
        System.out.println(jedis.hmget("user","qa","qa1")+""+jedis.hlen("user")+jedis.hexists("user","qa"));
        Iterator<String> iterators = jedis.hkeys("user").iterator();
        while (iterators.hasNext()) {
            String key = iterators.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }
        //del(String key)  del(String... keys)
        jedis.del("user");
        System.out.println(jedis.exists("user"));
    }

    @Test
    public void testList() {
        jedis.del("element");
        //lpush(String key, String... strings)
        jedis.lpush("element","spring","ibatis","redis");
        //lpush(String key, String... strings)
        System.out.println(jedis.lindex("element",1));

    }
}
