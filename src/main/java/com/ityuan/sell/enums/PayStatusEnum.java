package com.ityuan.sell.enums;

import lombok.Getter;

/**
 * Created by Yuan
 * 2017/12/22 10:25
 */
@Getter
public enum  PayStatusEnum {
    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;

    private String message;
    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
