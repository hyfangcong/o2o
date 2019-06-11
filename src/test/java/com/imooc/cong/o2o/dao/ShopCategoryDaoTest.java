package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.BaseTest;
import com.imooc.cong.o2o.entity.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/11
 */
public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    ShopCategoryDao shopCategoryDao;

    @Test
    public void queryAll(){
        List<ShopCategory> categories = shopCategoryDao.queryShopCategorys();
        Assert.assertEquals(2, categories.size());
    }
}
