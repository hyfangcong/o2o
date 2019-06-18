package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.BaseTest;
import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.entity.ShopCondition;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    ShopDao shopDao;

    @Test
    @Ignore
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
    @Ignore
    public void updateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("COCO奶茶");
        shop.setLastEditTime(new Date());
        int effectNum = shopDao.updateShop(shop);
        Assert.assertEquals(1, effectNum);
    }

    @Test
    @Ignore
    public void queryShopById(){
        int shopId = 1;
        Shop shop = shopDao.queryShopById(shopId);
        System.out.println();
    }

    @Test
    public void queryShopList(){
        ShopCondition shopCondition = new ShopCondition();
//        shopCondition.setOwnerId(1);
        shopCondition.setShopCategoryId(1);
        shopCondition.setShopName("es");
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 10);
        Assert.assertEquals(8,shopList.size());
    }

    @Test
    public void shopCount(){
        ShopCondition shopCondition = new ShopCondition();
        shopCondition.setShopCategoryId(1);
        shopCondition.setShopName("es");
        int count = shopDao.shopCount(shopCondition);
        System.out.println(count);
    }
}
