package com.ityuan.sell.converter;

import com.ityuan.sell.dataobject.OrderMaster;
import com.ityuan.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yuan
 * 2017/12/23 22:19
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e ->
                converter(e)
        ).collect(Collectors.toList());
    }
}
