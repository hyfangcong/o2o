package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.BaseTest;
import com.imooc.cong.o2o.entity.Shop;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    ShopDao shopDao;

    @Test
    public void testShopDao(){
        Shop shop = new Shop();
        shop.setOwnerId(1L);
        shop.setAreaId(2L);
        shop.setShopCategoryId(1L);
        shop.setShopName("test");
        shop.setPhone("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setShopImg("test");
        shop.setShopAddr("test");
        shop.setShopDesc("test");

        int effectNum = shopDao.insertShop(shop);
        Assert.assertEquals(1, effectNum);
    }

    @Test
    public void updateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("COCO奶茶");
        shop.setLastEditTime(new Date());
        int effectNum = shopDao.updateShop(shop);
        Assert.assertEquals(1, effectNum);
    }
}
