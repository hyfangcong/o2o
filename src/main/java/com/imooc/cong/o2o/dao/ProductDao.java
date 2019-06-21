package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.entity.Product;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
public interface ProductDao {
    /**
     * 新增商品
     * @param product {@link Product}
     * @return
     */
    int insertProduct(Product product);

    /**
     * 更新商品信息
     * @param product {@link Product}
     * @return
     */
    int updateProduct(Product product);

    /**
     * 根据shopId查询商品列表
     * @param shopId shopId
     * @return
     */
    List<Product> queryProductsByShopId(Integer shopId);

    /**
     * 根据productId查询商品信息
     * @param productId
     * @return
     */
    Product queryProductById(Integer productId);
}
