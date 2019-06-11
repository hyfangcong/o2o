package com.imooc.cong.o2o.dto;

import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.enums.ShopStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
@Data
public class ShopExecution {
    //结果状态
    private int state;

    //结果标识
    private String stateInfo;

    //店铺数量
    private int count;

    //操作的Shop（增删改的时候用到）
    private Shop shop;

    //shop列表，查询店铺列表的时候使用
    private List<Shop> shopList;

    public ShopExecution(){}

    /**
     * 店铺操作失败的时候使用的构造器
     * @param stateEnum
     */
    public ShopExecution(ShopStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 店铺操作成功的时候使用的构造器
     *
     */
    public ShopExecution(ShopStateEnum stateEnum, Shop shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    /**
     * 店铺操作成功的时候使用的构造器
     *
     */
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
