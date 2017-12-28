package com.ityuan.sell.dto;

import lombok.Data;

/**
 * 购物车
 * Created by Yuan
 * 2017/12/23 14:46
 */
@Data
public class CartDTO {
    /** 商品Id */
    private String productId;
    /** 数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
