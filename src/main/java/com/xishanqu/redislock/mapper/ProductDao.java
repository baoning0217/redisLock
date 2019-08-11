package com.xishanqu.redislock.mapper;


import com.xishanqu.redislock.common.request.ProductOrderParam;
import com.xishanqu.redislock.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品DAO
 * @Param
 * @Return
 * @Author BaoNing
 * @Time
 */
@Mapper
public interface ProductDao {

    /**
     * 增加
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    int insertSelective(Product record);

    /**
     * 查询
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    Product selectBySnKey(@Param("productSn") String productSn);

    /**
     * 修改
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    int updateBySnKeySelective(Product record);

    /**
     * 下单减库存
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    int reduceProductStock(ProductOrderParam productOrderParam);


    /**
     * mysql乐观锁减库存
     * @param productOrderParam
     * @return
     */
    int reduceProductStockForMysql(ProductOrderParam productOrderParam);


}