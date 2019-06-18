package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.BaseTest;
import com.imooc.cong.o2o.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    @Ignore
    public void add(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商铺类别5");
        productCategory.setShopId(1l);
        productCategory.setPriority(50);
        productCategory.setCreateTime(new Date());
        int effectRow = productCategoryDao.insert(productCategory);
        Assert.assertEquals(1, effectRow);
    }

    @Test
    public void selectAll(){
        List<ProductCategory> productCategories = productCategoryDao.queryListByShopId(1);
        Assert.assertEquals(5, productCategories.size());
    }

    @Test
    public void delete(){
        int effect = productCategoryDao.delete(3);
        Assert.assertEquals(1,effect);
    }

    @Test
    public void batchAdd(){
        List<ProductCategory> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryName("test");
            productCategory.setShopId(1l);
            productCategory.setPriority(0);
            productCategory.setCreateTime(new Date());
            list.add(productCategory);
        }
        int effect = productCategoryDao.batchInsert(list);
        Assert.assertEquals(5, effect);
    }
}
