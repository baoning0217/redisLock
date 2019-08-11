package com.xishanqu.redislock.dao;

import com.xishanqu.redislock.common.constant.RedisKeyConstant;
import com.xishanqu.redislock.common.request.OrderRequestParam;
import com.xishanqu.redislock.common.request.ProductOrderParam;
import com.xishanqu.redislock.config.RedissonManager;
import com.xishanqu.redislock.mapper.ProductDao;
import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 中文类名: 基于Redisson实现分布式锁
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
@Service
@Slf4j
public class RedissonLockOrderService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private RedissonManager redissonManager;


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
        RedissonClient redisson = redissonManager.getRedisson();
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            redissonLock.lock();
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
                        int result = orderService.saveOrder(requestParam, product);
                        return result;
                    }
                }
            }
        }finally {
            redissonLock.unlock();
        }
        return 0;
    }


}
