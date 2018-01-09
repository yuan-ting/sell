package com.ityuan.sell.enums;

import lombok.Getter;

/**
 * 订单状态
 * Created by Yuan
 * 2017/12/22 10:19
 */
@Getter     //get方法
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消")
    ;
    private Integer code;

    private String message;
    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
