package com.ityuan.sell.exception;

import com.ityuan.sell.enums.ResultEnum;

/**
 * Created by Yuan
 * 2017/12/23 12:11
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
