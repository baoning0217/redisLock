package com.xishanqu.redislock.utils;

/**
 * Created By BaoNing On 2019/3/18
 */
public class StringUtils {

    public static boolean isEmpty(Object o) {
        return org.springframework.util.StringUtils.isEmpty(o);
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

}
