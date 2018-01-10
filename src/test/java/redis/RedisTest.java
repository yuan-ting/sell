package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Created by Yuan
 * 2018/1/10 10:58
 */
@RunWith(SpringRunner.class)
public class RedisTest {

//    @Autowired
//    JedisPool jedisPool;
    
    @Test
    public void testR(){
        RedisCache redisCache = new RedisCache();
        JedisPool jedisPool = redisCache.redisPoolFactory();

        Jedis jedis = jedisPool.getResource();
        jedis.set("333","333");

        String result = jedis.get("333");
        System.out.println(result);
    }

}
