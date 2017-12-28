package com.ityuan.sell.dataobject;

import com.ityuan.sell.enums.OrderStatusEnum;
import com.ityuan.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Yuan
 * 2017/12/22 10:10
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态，默认0未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /** 创建时间*/
    private Date createTime;
    /** 修改时间*/
    private Date updateTime;
}
