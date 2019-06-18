package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.entity.ShopCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public interface ShopDao {

    /**
     * 根据店铺id查询店铺信息
     * @param shopId shopID
     * @return
     */
    Shop queryShopById(int shopId);


    /**
     * 新增商店
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新商店
     * @param shop
     * @return
     */
    int updateShop(Shop shop);

    /**
     * 查询商铺列表：查询条件可以为：owner，店铺名，区域，类别等
     *
     */
    List<Shop> queryShopList(@Param("shopCondition")ShopCondition shopCondition, @Param("rowIndex")Integer page,
                             @Param("pageSize")Integer size);

    /**
     * 商铺列表的数量，和queryshopList一起使用
     * @param shopCondition {@link ShopCondition}
     * @return int
     */
    int shopCount(@Param("shopCondition")ShopCondition shopCondition);
}
