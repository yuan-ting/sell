package com.ityuan.sell.service.impl;

import com.ityuan.sell.converter.OrderMaster2OrderDTOConverter;
import com.ityuan.sell.dataobject.OrderDetail;
import com.ityuan.sell.dataobject.OrderMaster;
import com.ityuan.sell.dataobject.ProductInfo;
import com.ityuan.sell.dto.CartDTO;
import com.ityuan.sell.dto.OrderDTO;
import com.ityuan.sell.enums.OrderStatusEnum;
import com.ityuan.sell.enums.PayStatusEnum;
import com.ityuan.sell.enums.ResultEnum;
import com.ityuan.sell.exception.SellException;
import com.ityuan.sell.repository.OrderDetailRepository;
import com.ityuan.sell.repository.OrderMasterRepository;
import com.ityuan.sell.service.OrderService;
import com.ityuan.sell.service.ProductService;
import com.ityuan.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yuan
 * 2017/12/23 11:57
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        //总价
        BigDecimal orderAmout = new BigDecimal(0);

//        List<CartDTO> cartDTOList = new ArrayList<>();

        //1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                //商品不存在
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算商品总价
            orderAmout = productInfo.getProductPrice()
                    //单价 * 数量
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    //再加上之前的总价
                    .add(orderAmout);

            //订单详情入库
            BeanUtils.copyProperties(productInfo, orderDetail); //把商品的属性copy到订单详情里

            orderDetail.setOrderId(orderId);   //订单Id
            orderDetail.setDetailId(KeyUtil.genUniqueKey());  //订单详情Id

            orderDetailRepository.save(orderDetail);

//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }
        //3.写入数据库（OrderMaster和OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmout(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】 订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态

        //返还库存

        //如果已支付，需要退款
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
