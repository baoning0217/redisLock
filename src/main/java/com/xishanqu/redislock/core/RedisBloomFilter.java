package com.xishanqu.redislock.core;

import com.google.common.base.Preconditions;
import com.xishanqu.redislock.common.constant.RedisKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 中文类名: Redis布隆过滤器
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/9/1
 */
@Component
public class RedisBloomFilter<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisBloomFilter(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    /**
     * 根据给定的布隆过滤器添加值
     * @param bloomFilterHelper
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value){
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset){
            redisTemplate.opsForValue().setBit(key, i, true);
        }
    }


    /**
     * 根据给定的布隆过滤器判断值是否存在
     * @param bloomFilterHelper
     * @param key
     * @param value
     * @param <T>
     * @return 存在返回false， 不存在返回true
     */
    public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value){
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset){
            Boolean result = redisTemplate.opsForValue().getBit(key, i);
            if (!result){
                return false;
            }
        }
        return true;
    }


    /**
     * 获取设置的Bloom Key
     * @param key
     * @return
     */
    public String getRedisBloomKey(String key){
        return RedisKeyConstant.BLOOM.BLOOM_KEY_PREFIX + key;
    }



}
