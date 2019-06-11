package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/11
 */
public interface ShopCategoryDao {
    /**
     * 查询所有的店铺分类
     * @return
     */
    List<ShopCategory> queryShopCategorys();
}
