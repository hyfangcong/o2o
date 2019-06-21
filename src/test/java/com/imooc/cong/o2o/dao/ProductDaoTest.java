package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.BaseTest;
import com.imooc.cong.o2o.entity.Product;
import com.imooc.cong.o2o.entity.ProductCategory;
import com.imooc.cong.o2o.entity.Shop;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
public class ProductDaoTest extends BaseTest {
    @Autowired
    ProductDao productDao;

    @Test
    @Ignore
    public void insert(){
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1l);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(20l);
        product.setProductName("双皮奶昔");
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setPriority(10);
        product.setNormalPrice("20");
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        int effect = productDao.insertProduct(product);
        Assert.assertEquals(1, effect);
    }

    @Test
    public void queryListByShopId(){
        List<Product> list = productDao.queryProductsByShopId(1);
        Assert.assertEquals(2,list.size());
    }

    @Test
    public void queryByProductId(){
        Product product = productDao.queryProductById(1);

    }

    @Test
    public void updateProductTest(){
        Product product = new Product();
        product.setProductId(1l);
        Shop shop = new Shop();
        shop.setShopId(1l);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(20l);
        product.setProductName("双皮奶昔");
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setPriority(10);
        product.setNormalPrice("20");
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        int effect = productDao.updateProduct(product);
        Assert.assertEquals(1,effect);
    }

}
