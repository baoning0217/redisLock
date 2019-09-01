package com.xishanqu.redislock.service;

import com.xishanqu.redislock.common.constant.IDKeyConstant;
import com.xishanqu.redislock.common.constant.RedisKeyConstant;
import com.xishanqu.redislock.config.RedisConfig;
import com.xishanqu.redislock.core.RedisBloomFilter;
import com.xishanqu.redislock.mapper.ProductDao;
import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.utils.SequenceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisBloomFilter redisBloomFilter;
    @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 初始化数据，将数据添加到布隆过滤器
     */
    public void initData(){
        List<Product> allProducts = productDao.selectAllProduct();
        if (!ObjectUtils.isEmpty(allProducts)){
            allProducts.stream().forEach(product -> {
                redisBloomFilter.addByBloomFilter(redisConfig.initBloomFilterHelper(), redisBloomFilter.getRedisBloomKey(product.getSn()), product.getSn());
            });
        }
    }


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


    /**
     * 查询商品
     * @param productSn
     * @return
     */
    public Product selectByProductSn(String productSn){
        Product product = null;
        //检测布隆过滤器中是否已经存了商品的sn
        boolean filter = redisBloomFilter.includeByBloomFilter(redisConfig.initBloomFilterHelper(), redisBloomFilter.getRedisBloomKey(productSn), productSn);
        if (!filter){
            log.info("布隆过滤器中不存在该数据");
            return product;
        }
        //从redis缓存中获取数据
        product = (Product)redisTemplate.opsForValue().get(RedisKeyConstant.PRODUCT_MODULE.PRODUCT_KEY + productSn);
        if (!ObjectUtils.isEmpty(product)){
            return product;
        }
        //从数据库中获取数据
        product = productDao.selectBySnKey(productSn);
        if (!ObjectUtils.isEmpty(product)){
            //设置缓存
            redisTemplate.opsForValue().setIfAbsent(RedisKeyConstant.PRODUCT_MODULE.PRODUCT_KEY + product.getSn(), product, 600, TimeUnit.SECONDS);
        }
        return product;
    }


}
