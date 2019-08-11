package com.xishanqu.redislock.controller;


import com.xishanqu.redislock.common.base.ResultEntity;
import com.xishanqu.redislock.common.constant.ErrorConstant;
import com.xishanqu.redislock.common.request.OrderRequestParam;
import com.xishanqu.redislock.dao.MySqlLockOrderService;
import com.xishanqu.redislock.dao.RedisLockOrderService;
import com.xishanqu.redislock.dao.RedissonLockOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 中文类名: 订单接口
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/8/4
 */
@RestController
@RequestMapping("/api/sys/order")
@Slf4j
public class OrderController {

    @Autowired
    private RedissonLockOrderService redissonLockOrderService;

    @Autowired
    private MySqlLockOrderService mySqlLockOrderService;

    @Autowired
    private RedisLockOrderService redisLockOrderService;


    /**
     * 生成订单接口
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    @PostMapping("/save")
    public ResultEntity saveOrder(@RequestBody OrderRequestParam requestParam){
        if (ObjectUtils.isEmpty(requestParam.getBuyUserId())){
            return new ResultEntity(ErrorConstant.USER.USER_ID_LACK);
        }
        if (ObjectUtils.isEmpty(requestParam.getProductSn())){
            return new ResultEntity(ErrorConstant.PRODUCT.PRODUCT_ID_LACK);
        }
        int result = redisLockOrderService.saveOrder(requestParam);
        if (result > 0){
            return new ResultEntity("200","购买成功");
        }else {
            return new ResultEntity("500","购买失败");
        }
    }


}
