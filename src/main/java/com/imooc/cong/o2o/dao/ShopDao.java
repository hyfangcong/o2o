package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.entity.Shop;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public interface ShopDao {
    /**
     * 新增商店
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新商店
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
