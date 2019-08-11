package com.xishanqu.redislock.common.constant;

/**
 * 中文类名: 统一异常定义
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
public class ErrorConstant {

    /**
     * 登录异常
     */
    public static final class LOGIN {
        public static final String LOGIN_PARAM_LACK = "登录参数缺少";
    }

    /**
     * 用户模块异常
     */
    public static final class USER {
        public static final String USER_ID_LACK = "用户id不存在";
    }

    /**
     * 商品模块
     */
    public static final class PRODUCT {
        public static final String PRODUCT_ID_LACK = "商品id不存在";
    }



}
