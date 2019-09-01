package com.xishanqu.redislock.controller;

import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
@RestController
@RequestMapping("/api/sys/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * 初始话布隆过滤器数据
     */
    @GetMapping("/init")
    public void initData(){
        productService.initData();
    }


    /**
     * 新增
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    @PostMapping("/save")
    public int saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }


    /**
     * 查询
     * @param productSn
     * @return
     */
    @GetMapping("/selectBySn")
    public Product selectProduct(@RequestParam("sn") String productSn){
        log.info("selectSn request={}", productSn);
        return productService.selectByProductSn(productSn);
    }


}
