package com.ldp.springboot.config;

import com.ldp.springboot.bean.Department;
import com.ldp.springboot.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Return
 * @create 2019-05-28 15:24
 */
//这是一个redis的配置类
@Configuration
public class RedisConfig {

    //定义关于的employee的redisTemplate
    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate
            (RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Employee> template =
                new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    //管理员工的缓存信息的cacheManager
    @Primary
    @Bean
    public RedisCacheManager empCacheManager(RedisTemplate<Object, Employee> empRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(empRedisTemplate);
        //设置储存的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }


    //向容器注入关于的department的RedisTemplate
    @Bean
    public RedisTemplate<Object, Department> deptRedisTemplate
    (RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Department> template =
                new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    public RedisCacheManager deptCacheManager(RedisTemplate<Object, Department> deptRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
        //设置储存的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
