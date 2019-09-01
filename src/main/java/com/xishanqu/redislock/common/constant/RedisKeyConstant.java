package com.xishanqu.redislock.common.constant;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
public class RedisKeyConstant {

    /**
     * 商品模块KEY
     */
    public static final class PRODUCT_MODULE {
        public static final String PRODUCT_KEY = "product:";
    }


    /**
     * 布隆过滤器的键在Redis中的前缀，统计过滤器对Redis的使用情况
     */
    public static final class BLOOM {
        public static final String BLOOM_KEY_PREFIX = "bloom:";
    }


}
