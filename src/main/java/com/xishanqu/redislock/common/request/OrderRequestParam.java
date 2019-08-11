package com.xishanqu.redislock.common.request;

import lombok.Data;

/**
 * 中文类名: 订单请求参数
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
@Data
public class OrderRequestParam {

    /**
     * 商品编号
     */
    private String productSn;

    /**
     * 客户ID
     */
    private String buyUserId;

    /**
     * 购买数量
     */
    private Integer purchaseCount;

}
