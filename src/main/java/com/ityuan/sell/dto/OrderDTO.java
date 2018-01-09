package com.ityuan.sell.dto;

import com.ityuan.sell.dataobject.OrderDetail;
import com.ityuan.sell.enums.OrderStatusEnum;
import com.ityuan.sell.enums.PayStatusEnum;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ityuan.sell.dataobject.OrderDetail;
import com.ityuan.sell.enums.OrderStatusEnum;
import com.ityuan.sell.enums.PayStatusEnum;
import com.ityuan.sell.utils.serializer.Date2LongSerializer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单传输对象
 * Created by Yuan
 * 2017/12/23 11:48
 */
@Data
public class OrderDTO {
    /**订单Id */
    private String orderId;
    /** 买家名称 */
    private String buyerName;
    /** 买家电话*/
    private String buyerPhone;
    /** 买家地址 */
    private String buyerAddress;
    /** 买家微信openid*/
    private String buyerOpenid;
    /** 订单总金额 */
    private BigDecimal orderAmout;
    /** 订单状态,默认0新下单 */
    private Integer orderStatus;
    /** 支付状态，默认0未支付 */
    private Integer payStatus;
    /** 创建时间*/

    private Date createTimeDate;
    /** 修改时间*/

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /** 修改时间*/
    @JsonSerialize(using = Date2LongSerializer.class)

    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
