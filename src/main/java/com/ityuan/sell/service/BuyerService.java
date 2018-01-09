package com.ityuan.sell.service;

import com.ityuan.sell.dto.OrderDTO;

/**
 * 买家
 * Created by Yuan
 * 2018/1/3 10:35
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);
    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
