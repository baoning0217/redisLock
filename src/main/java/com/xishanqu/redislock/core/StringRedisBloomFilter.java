package com.xishanqu.redislock.core;

import com.google.common.base.Preconditions;
import com.xishanqu.redislock.common.constant.RedisKeyConstant;
import com.xishanqu.redislock.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/9/1
 */
@Component
public class StringRedisBloomFilter {

    @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 根据给定的布隆过滤器添加值
     * @param key
     * @param value
     */
    public void addByBloomFilter(String key, String value){
        BloomFilterHelper<String> stringBloomFilterHelper = redisConfig.initBloomFilterHelper();
        Preconditions.checkArgument(stringBloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = stringBloomFilterHelper.murmurHashOffset(value);
        for (int i : offset){
            redisTemplate.opsForValue().setBit(key, i, true);
        }
    }


    /**
     * 根据给定的布隆过滤器判断值是否存在
     * @param key
     * @param value
     * @return 存在返回false， 不存在返回true
     */
    public boolean includeByBloomFilter(String key, String value){
        BloomFilterHelper<String> stringBloomFilterHelper = redisConfig.initBloomFilterHelper();
        Preconditions.checkArgument(stringBloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = stringBloomFilterHelper.murmurHashOffset(value);
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
