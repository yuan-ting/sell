package com.ityuan.sell.repository;

import com.ityuan.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Yuan
 * 2017/12/22 10:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("袁挺");
        orderMaster.setBuyerPhone("13750003463");
        orderMaster.setBuyerAddress("深圳");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmout(new BigDecimal(16));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageRequest = new PageRequest(1, 3);

        Page<OrderMaster> result = repository.findByBuyerOpenid("110110",pageRequest);
        Assert.assertNotEquals(0,result.getTotalElements());
       // System.out.println(result.getTotalElements());
    }

}