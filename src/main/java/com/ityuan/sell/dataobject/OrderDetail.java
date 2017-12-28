package com.ityuan.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情表
 * Created by Yuan
 * 2017/12/22 10:30
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;

    /** 订单Id*/
    private String orderId;
    /** 商品Id*/
    private String productId;
    /** 商品名称*/
    private String productName;
    /** 商品价格*/
    private BigDecimal productPrice;
    /** 商品数量*/
    private Integer productQuantity;
    /** 商品图片*/
    private String productIcon;

}
