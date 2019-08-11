package com.xishanqu.redislock.mapper;

import com.xishanqu.redislock.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * OrderDao继承基类
 */
@Mapper
public interface OrderDao {

    /**
     * 查询
     * @Param orderNo
     * @Return order
     * @Author BaoNing
     * @Time
     */
    Order selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 增加
     * @Param
     * @Return int
     * @Author BaoNing
     * @Time
     */
    int insertSelective(Order order);

    /**
     * 修改
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    int updateByOrderNoSelective(Order order);

}