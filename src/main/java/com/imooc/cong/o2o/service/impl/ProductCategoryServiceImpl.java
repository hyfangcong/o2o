package com.imooc.cong.o2o.service.impl;

import com.imooc.cong.o2o.dao.ProductCategoryDao;
import com.imooc.cong.o2o.entity.ProductCategory;
import com.imooc.cong.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getListByShopId(Integer shopId) {
        return productCategoryDao.queryListByShopId(shopId);
    }

    @Override
    public int addProductCategory(ProductCategory productCategory) {
        productCategory.setCreateTime(new Date());
        return productCategoryDao.insert(productCategory);
    }

    @Override
    public int batchAdd(List<ProductCategory> productCategories) {
        productCategories.forEach(item ->{
            item.setCreateTime(new Date());
        });
        return productCategoryDao.batchInsert(productCategories);
    }

    @Override
    public int delete(Integer id) {
        return productCategoryDao.delete(id);
    }
}
