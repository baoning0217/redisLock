package com.xishanqu.redislock.common.constant;

/**
 * 自定义ID前缀
 * @author: baoning
 * @date: 2019/8/2 17:46
 */
public class IDKeyConstant {

    /**
     * 用户模块
     */
    public static final class USER {
        //用户编号
        public static final String SYS_USER_ID = "syi";
    }


    /**
     * 商品模块
     */
    public static final class PRODUCT {
        //商品编号
        public static final String PRODUCT_KEY_SN = "pks";
    }


    /**
     * 订单模块
     */
    public static final class ORDER {
        //订单编号
        public static final String ORDER_KEY_NO = "okn";
    }

}
