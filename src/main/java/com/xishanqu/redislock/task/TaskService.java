package com.xishanqu.redislock.task;

import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/9/3
 */
@Component
@Slf4j
public class TaskService {

    @Autowired
    private ProductService productService;

    public static final String[] ids = {"pks201909012020486133222422562","pks201909012014484957278838103","pks201909012010498567479306971","pks201909012009572269627362010","pks201909012009028827441195884","pks201908091535366272088203697"};


    @Scheduled(cron = "*/30 * * * * ?")
    public void getObject(){
        String id = ids[byMathRandom()];
        Product product = productService.selectByProductSn(id);
        log.info("输出查询结果:", product);
        System.out.println("查询结果:" + product);
    }


    /**
     * 取整数随机数
     * @return
     */
    private static Integer byMathRandom() {
        int max=5,min=0;
        return (int)(Math.random()*(max-min)+min);
    }




}
