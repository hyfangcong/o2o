package com.imooc.cong.o2o.entity;

import java.util.Map;

/**
 * @author: fangcong
 * @date: 2019/6/16
 */
public class ShopTest {
    Map<String,Object> shpStr;
    Integer areaId;
    Integer shopCategoryId;

    public Map<String, Object> getShpStr() {
        return shpStr;
    }

    public void setShpStr(Map<String, Object> shpStr) {
        this.shpStr = shpStr;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Integer shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }
}
