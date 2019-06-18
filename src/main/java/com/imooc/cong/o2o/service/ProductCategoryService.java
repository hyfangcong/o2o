package com.imooc.cong.o2o.service;

import com.imooc.cong.o2o.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
public interface ProductCategoryService {
    /**
     * 根据shopId查询商品类别列表
     * @param shopId shopId
     * @return
     */
    List<ProductCategory> getListByShopId(Integer shopId);

    /**
     * 新增一个商品类别
     * @param productCategory {@link ProductCategory}
     * @return
     */
    int addProductCategory(ProductCategory productCategory);

    /***
     * 批量新增
     * @param productCategories List
     * @return
     */
    int batchAdd(List<ProductCategory> productCategories);
    /**
     * 删除一个商品类别
     * @param id id
     * @return
     */
    int delete(Integer id);
}
