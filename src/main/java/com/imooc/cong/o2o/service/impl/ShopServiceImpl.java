package com.imooc.cong.o2o.service.impl;

import com.imooc.cong.o2o.dao.ShopDao;
import com.imooc.cong.o2o.dto.ShopExecution;
import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.entity.ShopCondition;
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
import java.util.List;

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

    @Override
    public ShopExecution getByShopId(int shopId) {
        ShopExecution shopExecution = new ShopExecution();
        Shop shop = shopDao.queryShopById(shopId);
        if(shop == null){
            shopExecution.setState(ShopStateEnum.NULL_SHOP.getState());
            shopExecution.setStateInfo(ShopStateEnum.NULL_SHOP.getStateInfo());
        }else{
            shopExecution.setState(ShopStateEnum.SUCCESS.getState());
            shopExecution.setStateInfo(ShopStateEnum.SUCCESS.getStateInfo());
            shopExecution.setShop(shop);
        }
        return shopExecution;
    }

    /**
     * 修改店铺
     * @param shop shopId is not null
     * @param shopImg
     * @return
     */
    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) {
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            int effectNum;
            if (shopImg != null) {
                addShopImg(shop, shopImg);
            }
//            shop.setOwnerId(1L); //这里应该是从session中获取的
            shop.setLastEditTime(new Date());
             effectNum = shopDao.updateShop(shop);
            if(effectNum <= 0){
                throw new ShopOperationException("店铺修改失败");
            }
        }
        catch (Exception e){
            throw new ShopOperationException("add shop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.SUCCESS, shop);
    }


    @Override
    public ShopExecution getShopList(ShopCondition shopCondition, int pageIndex, int pageSize) {
        ShopExecution shopExecution = new ShopExecution();
        List<Shop> shopList = shopDao.queryShopList(shopCondition, pageIndex, pageSize);
        shopExecution.setState(ShopStateEnum.SUCCESS.getState());
        shopExecution.setStateInfo("操作成功");
        shopExecution.setShopList(shopList);
        return shopExecution;
    }

    @Override
    public int shopCount(ShopCondition shopCondition) {
        return shopDao.shopCount(shopCondition);
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
