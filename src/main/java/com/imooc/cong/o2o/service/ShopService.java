package com.imooc.cong.o2o.service;

import com.imooc.cong.o2o.dto.ShopExecution;
import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.entity.ShopCondition;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public interface ShopService {
    /**
     * 新增一个店铺
     * @param shop Shop
     * @param shopImg CommonsMultipartFile
     * @return ShopExecution
     */
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);

    /**
     * 查询指定店铺信息
     * @param shopId shopId
     * @return ShopExecution
     */
    ShopExecution getByShopId(int shopId);

    /**
     * 修改店铺信息
     * @param shop shopId is not null
     * @param shopImg
     * @return
     */
    ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg);


    /**
     * 分页查询查询店铺列表
     * @param shopCondition {@link ShopCondition}
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    ShopExecution getShopList(ShopCondition shopCondition, int pageIndex, int pageSize);

    /**
     * 商铺总数，和getShopList一起使用进行分页查询
     * @param shopCondition {@link ShopCondition}
     * @return
     */
    int shopCount(ShopCondition shopCondition);

}
