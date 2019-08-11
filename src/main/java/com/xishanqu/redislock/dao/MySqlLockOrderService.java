package com.xishanqu.redislock.dao;

import com.xishanqu.redislock.common.request.OrderRequestParam;
import com.xishanqu.redislock.common.request.ProductOrderParam;
import com.xishanqu.redislock.mapper.ProductDao;
import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/11
 */
@Service
@Slf4j
public class MySqlLockOrderService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductDao productDao;


    /**
     * 保存订单
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveOrder(OrderRequestParam requestParam){
        for (int i = 0; i < 3; i++){
            //取出购买商品
            Product product = productDao.selectBySnKey(requestParam.getProductSn());
            //没有库存
            if (product.getStock() == 0) {
                return 0;
            }
            //库存存在,且足够购买
            if (product.getStock() >= requestParam.getPurchaseCount()) {
            //校验库存
                //减库存
                ProductOrderParam productOrderParam = new ProductOrderParam();
                productOrderParam.setProductSn(requestParam.getProductSn());
                productOrderParam.setPurchaseCount(requestParam.getPurchaseCount());
                productOrderParam.setVersion(product.getVersion());
                int update = productDao.reduceProductStockForMysql(productOrderParam);
                if (update == 0) {
                    continue;
                }
                //下单操作
                int status = orderService.saveOrder(requestParam, product);
                return status;
            }else {
                return 0;
            }
        }
        //库存不够,或不够购买数量
        return 0;
    }

}
