package com.ityuan.sell.repository;

import com.ityuan.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Yuan
 * 2017/12/22 10:40
 */
public interface OrderDetailRepository  extends JpaRepository<OrderDetail, String>{

    List<OrderDetail> findByOrderId(String orderId);
}
