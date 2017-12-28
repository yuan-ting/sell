package com.ityuan.sell.repository;

import com.ityuan.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /** 查询上架的商品 */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
