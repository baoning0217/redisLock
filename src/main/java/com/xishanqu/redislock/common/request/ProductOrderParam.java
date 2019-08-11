package com.xishanqu.redislock.common.request;

import lombok.Data;

/**
 * 中文类名: 商品减库存操作参数
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
@Data
public class ProductOrderParam {

    /**
     * 商品编号
     */
    private String productSn;

    /**
     * 购买数量
     */
    private Integer purchaseCount;

    /**
     * 版本数据
     */
    private Integer version;

}
