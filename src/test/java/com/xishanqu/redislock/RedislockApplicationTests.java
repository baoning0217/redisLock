package com.xishanqu.redislock;

import com.xishanqu.redislock.config.RedisConfig;
import com.xishanqu.redislock.core.RedisBloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedislockApplicationTests {

    @Autowired
    private RedisBloomFilter redisBloomFilter;
    @Autowired
    private RedisConfig redisConfig;

    @Test
    public void contextLoads() {



//        BloomFilterHelper<String> bloomFilterHelper = redisConfig.initBloomFilterHelper();
//        String key = RedisKeyConstant.BLOOM.BLOOM_KEY_PREFIX;
//
//        int j =0;
//        for (int i =0; i< 100; i++){
//            redisBloomFilter.addByBloomFilter(bloomFilterHelper, key, i+"");
//        }
//        for (int i = 0; i < 1000; i++){
//            boolean result = redisBloomFilter.includeByBloomFilter(bloomFilterHelper, key, i + "");
//            if (!result){
//                j++;
//            }
//        }
//        System.out.println("漏掉了" + j + "个" );
    }



}
