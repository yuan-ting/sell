package com.ityuan.sell.repository;

import com.ityuan.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Created by Yuan
 * 2017/12/22 10:36
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String>{

    /** 根据买家的ID来分页查询*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
