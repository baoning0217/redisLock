package com.xishanqu.redislock.service;

import com.xishanqu.redislock.common.constant.IDKeyConstant;
import com.xishanqu.redislock.mapper.ProductDao;
import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.utils.SequenceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductDao productDao;


    /**
     * 添加商品
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    public int saveProduct(Product product){
        //设置商品编码
        product.setSn(SequenceUtils.genKey(IDKeyConstant.PRODUCT.PRODUCT_KEY_SN, 10));
        product.setCreateTime(new Date());
        return productDao.insertSelective(product);
    }


}
