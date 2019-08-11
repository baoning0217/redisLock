package com.xishanqu.redislock.controller;

import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
@RestController
@RequestMapping("/api/sys/product")
public class ProductController {

    @Autowired
    private ProductService productService;


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


}
