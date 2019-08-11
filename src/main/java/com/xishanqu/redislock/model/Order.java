package com.xishanqu.redislock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * t_order
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品编号
     */
    private String productSn;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 单价
     */
    private Double price;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 总价
     */
    private Double amount;

    /**
     * 客户id
     */
    private String buyUserId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 客户姓名
     */
    private String buyName;

    /**
     * 客户地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}