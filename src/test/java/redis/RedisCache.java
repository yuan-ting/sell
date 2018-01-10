package redis;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by Yuan
 * 2018/1/10 10:44
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisCache extends CachingConfigurerSupport {

//    @Value("${spring.redis.host}")
    private String host = "localhost";

 //   @Value("${spring.redis.prot}")
    private int port = 6379;

 //   @Value("${spring.redis.pool.max-idle}")
    private int maxIdle = 8;

//    @Value("${spring.redis.pool.max-wait}")
    private int maxWaitMillis = 1;

    @Bean
    public JedisPool redisPoolFactory(){
        log.info("JedisPool注入成功");
        log.info("redis地址：" + host + ":" + port);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        JedisPool jedisPool = new JedisPool(host,port);

        return jedisPool;
    }

    @Bean
    public ShardedJedisPool convertJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        return null;
    }
}
