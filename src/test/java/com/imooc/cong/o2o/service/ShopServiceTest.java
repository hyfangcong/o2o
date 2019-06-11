package com.imooc.cong.o2o.service;

import com.imooc.cong.o2o.BaseTest;
import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void addShopTest(){
        Shop shop = new Shop();
        shop.setOwnerId(1L);
        shop.setAreaId(2L);
        shop.setShopCategoryId(1L);
        shop.setShopName("test1");
        shop.setPhone("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setShopAddr("test1");
        shop.setShopDesc("test1");

//        CommonsMultipartFile cFile = new CommonsMultipartFile("");
    }

}
