package com.xishanqu.redislock.service;

import com.xishanqu.redislock.common.constant.IDKeyConstant;
import com.xishanqu.redislock.common.request.OrderRequestParam;
import com.xishanqu.redislock.mapper.OrderDao;
import com.xishanqu.redislock.mapper.ProductDao;
import com.xishanqu.redislock.model.Order;
import com.xishanqu.redislock.model.Product;
import com.xishanqu.redislock.model.SysUser;
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
 * @Time 2019/8/11
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private SysUserService sysUserService;


    /**
     * 下单
     * @Param
     * @Return
     * @Author BaoNing
     * @Time
     */
    public int saveOrder(OrderRequestParam requestParam, Product product){
        //当前客户信息
        SysUser user = sysUserService.querySysUser(requestParam.getBuyUserId());
        //下单操作
        Order order = new Order();
        order.setOrderNo(SequenceUtils.genKey(IDKeyConstant.ORDER.ORDER_KEY_NO,10));
        order.setProductSn(product.getSn());
        order.setProductName(product.getName());
        order.setPrice(product.getPrice());
        order.setQuantity(requestParam.getPurchaseCount());
        double totalPrice = product.getPrice() * requestParam.getPurchaseCount();
        order.setAmount(totalPrice);
        order.setBuyUserId(user.getSysUserId());
        order.setAddress(user.getAddress());
        order.setPhone(user.getMobile());
        order.setBuyName(user.getName());
        order.setCreateTime(new Date());
        //下单
        int result = orderDao.insertSelective(order);
        log.info("下单用户姓名:user={}", user.getName() );
        return result;
    }


}
