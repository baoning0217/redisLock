package com.xishanqu.redislock.dao;

import com.xishanqu.redislock.common.constant.RedisKeyConstant;
import com.xishanqu.redislock.common.request.OrderRequestParam;
import com.xishanqu.redislock.common.request.ProductOrderParam;
import com.xishanqu.redislock.mapper.ProductDao;
import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/11
 */
@Service
@Slf4j
public class RedisLockOrderService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 保存订单
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    @Transactional(rollbackFor = Exception.class)
    public int saveOrder(OrderRequestParam requestParam){
        String lockKey = RedisKeyConstant.PRODUCT_MODULE.PRODUCT_KEY;
        String lockValue = UUID.randomUUID().toString();
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 60, TimeUnit.SECONDS);
        if (!result){
            return 0;
        }
        try {
            Product product = productDao.selectBySnKey(requestParam.getProductSn());
            if (product.getStock() == 0){
                return 0;
            }
            if (product.getStock() >= requestParam.getPurchaseCount()){
                if (product.getStock() >= requestParam.getPurchaseCount()){
                    ProductOrderParam productOrderParam = new ProductOrderParam();
                    productOrderParam.setProductSn(requestParam.getProductSn());
                    productOrderParam.setPurchaseCount(requestParam.getPurchaseCount());
                    int res = productDao.reduceProductStock(productOrderParam);
                    if (res > 0){
                        int status = orderService.saveOrder(requestParam, product);
                        return status;
                    }
                }
            }
        }finally {
            if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))){
                redisTemplate.delete(lockKey);
            }
        }
        return 0;
    }

}
