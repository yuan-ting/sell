package com.ityuan.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 */
@Getter         //get方法
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;

    private String message;
    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
