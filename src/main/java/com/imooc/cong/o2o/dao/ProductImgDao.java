package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.entity.ProductImg;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/19
 */
public interface ProductImgDao {

    /**
     * 根据productImgId查询详情图片
     * @param productImgId
     * @return
     */
    ProductImg queryProductImgById(Long productImgId);

    /**
     * 根据productId查询商品的详情图
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量插入商品的详情图
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 删除商品的所有详情图
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);

    /**
     * 删除商品的单张详情图
     * @param productImgId
     * @return
     */
    int deleteProductImgByProductImgId(long productImgId);
}
