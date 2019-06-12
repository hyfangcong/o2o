package com.imooc.cong.o2o.service.impl;

import com.imooc.cong.o2o.dao.ShopDao;
import com.imooc.cong.o2o.dto.ShopExecution;
import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.enums.ShopStateEnum;
import com.imooc.cong.o2o.exception.ShopOperationException;
import com.imooc.cong.o2o.service.ShopService;
import com.imooc.cong.o2o.util.ImageUtil;
import com.imooc.cong.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    /**
     * 注册店铺
     * @param shop
     * @param shopImg
     * @return
     */
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //给shop赋初始值
            shop.setOwnerId(1L); //测试账号
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectNum = shopDao.insertShop(shop);
            if(effectNum <= 0){
                throw new ShopOperationException("店铺创建失败");
            }

            /**
             * 先对店铺进行插入，然后拿到shopId后再存储图片
             * 图片的存储路径中需要要用到shopId
             */
            addShopImg(shop, shopImg);
            effectNum = shopDao.updateShop(shop);
            if(effectNum <= 0){
                throw new ShopOperationException("图片存储失败");
            }
        }
        catch (Exception e){
            throw new ShopOperationException("add shop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    /**
     * 存储图片到指定的位置
     * @param shop
     * @param shopImg
     */
    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        //获取图片目录的相对路径
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}
