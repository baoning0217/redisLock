package com.xishanqu.redislock.config;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.xishanqu.redislock.core.BloomFilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;

/**
 * @Author BaoNing
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 预计插入量
     */
    @Value("${spring.redis.bloom.expectedInsertions}")
    private int expectedInsertions;

    /**
     * 可接受错误率
     */
    @Value("${spring.redis.bloom.fpp}")
    private double fpp;


    @Bean
    public RedisTemplate redisTemplateInit() {
        //设置序列化Key的实例化对象
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置序列化Value的实例化对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * 注册布隆过滤器
     * @return
     */
    @Bean
    @PostConstruct
    public BloomFilterHelper<String> initBloomFilterHelper(){
        return new BloomFilterHelper<>(
                (Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8).putString(from, Charsets.UTF_8),
                expectedInsertions,
                fpp);

    }


}
