package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.entity.ProductCategory;
import com.imooc.cong.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
public interface ProductCategoryDao {
    /**
     * 查询店铺中的商品分类
     * @param shopId shopId
     * @return
     */
    List<ProductCategory> queryListByShopId(Integer shopId);

    /**
     * 新增一个商品类别
     * @param productCategory {@link ShopCategory}
     * @return
     */
    int insert(ProductCategory productCategory);

    /**
     * 批量新增
     * @param productCategories List
     * @return
     */
    int batchInsert(List<ProductCategory> productCategories);

    /**
     * 删除一个商品类别
     * @param id id
     * @return
     */
    int delete(Integer id);
}
