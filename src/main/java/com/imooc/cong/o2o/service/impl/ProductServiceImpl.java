package com.imooc.cong.o2o.service.impl;

import com.imooc.cong.o2o.dao.ProductDao;
import com.imooc.cong.o2o.dao.ProductImgDao;
import com.imooc.cong.o2o.dto.ProductExecution;
import com.imooc.cong.o2o.entity.Product;
import com.imooc.cong.o2o.entity.ProductImg;
import com.imooc.cong.o2o.enums.ProductStateEnum;
import com.imooc.cong.o2o.service.ProductService;
import com.imooc.cong.o2o.util.ImageUtil;
import com.imooc.cong.o2o.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductImgDao productImgDao;

    @Override
    public ProductExecution getProductsByShopId(Integer shopId) {
        ProductExecution pe = new ProductExecution();
        List<Product> products = productDao.queryProductsByShopId(shopId);
        if(products !=null && products.size() > 0){
            pe.setState(ProductStateEnum.SUCCESS.getState());
            pe.setStateInfo(ProductStateEnum.SUCCESS.getStateInfo());
            pe.setProductList(products);
        }else{
            pe.setState(ProductStateEnum.EMPTY.getState());
            pe.setStateInfo(ProductStateEnum.EMPTY.getStateInfo());
        }
        return pe;
    }

    @Override
    public ProductExecution getProductById(Integer productId) {
        ProductExecution pe = new ProductExecution();
        Product product = productDao.queryProductById(productId);
        if(product != null){
            pe.setState(ProductStateEnum.SUCCESS.getState());
            pe.setStateInfo(ProductStateEnum.SUCCESS.getStateInfo());
            pe.setProduct(product);
        }else{
            pe.setState(ProductStateEnum.EMPTY.getState());
            pe.setStateInfo(ProductStateEnum.EMPTY.getStateInfo());
        }
        return pe;
    }


    @Override
    @Transactional
    public ProductExecution addProduct(Product product, CommonsMultipartFile smallImg,
                                       List<CommonsMultipartFile>productImgs) {
        ProductExecution pe = new ProductExecution();
        int effectRow =  productDao.insertProduct(product);
        if(effectRow <= 0){
            throw new RuntimeException("商品插入失败");
        }
        if(smallImg != null){
            addSmallImg(product,smallImg);
        }
        if(productImgs != null && productImgs.size() > 0){
            addProductImgs(product, productImgs);
        }
        effectRow = productDao.updateProduct(product);
        if(effectRow <= 0){
            throw new RuntimeException("更新商品缩略图失败");
        }
        pe.setState(ProductStateEnum.SUCCESS.getState());
        pe.setStateInfo(ProductStateEnum.SUCCESS.getStateInfo());
        return pe;
    }

    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, CommonsMultipartFile smallImg,
                                          List<CommonsMultipartFile> productImgs) {
        ProductExecution pe = new ProductExecution();
        if(product == null || product.getShop() == null || product.getShop().getShopId() <= 0){
            throw new RuntimeException("product is null or product shopId is illegal");
        }
        int effectRow;
        try{
            if(smallImg != null){
                addSmallImg(product, smallImg);
            }
            if(productImgs != null && productImgs.size() > 0){
                deleteProductImgs(product.getShop().getShopId());
                addProductImgs(product,productImgs);
            }
            effectRow = productDao.updateProduct(product);
            if(effectRow <= 0){
                pe.setState(ProductStateEnum.INNER_ERROR.getState());
                pe.setStateInfo(ProductStateEnum.INNER_ERROR.getStateInfo());
            }else{
                pe.setState(ProductStateEnum.SUCCESS.getState());
                pe.setStateInfo(ProductStateEnum.SUCCESS.getStateInfo());
            }
        }catch (Exception e){
            pe.setState(ProductStateEnum.INNER_ERROR.getState());
            pe.setStateInfo(ProductStateEnum.INNER_ERROR.getStateInfo());
        }
        return pe;
    }

    @Override
    public ProductExecution modifyProduct(Product product) {
        ProductExecution pe = new ProductExecution();
        int effect = productDao.updateProduct(product);
        if(effect <= 0){
            pe.setState(ProductStateEnum.INNER_ERROR.getState());
            pe.setStateInfo(ProductStateEnum.INNER_ERROR.getStateInfo());
        }else{
            pe.setState(ProductStateEnum.SUCCESS.getState());
            pe.setStateInfo(ProductStateEnum.SUCCESS.getStateInfo());
        }
        return pe;
    }

    /**
     * 存储缩略图片到指定的位置
     * @param product
     */
    private void addSmallImg(Product product, CommonsMultipartFile smallImg) {
        //获取图片目录的相对路径
        String dest = FileUtil.getShopImgPath(product.getShop().getShopId());
        String productImgAddr = ImageUtil.generateThumbnail(smallImg, dest);
        product.setImgAddr(productImgAddr);
    }

    /**
     * 存错详情图
     * @param product
     * @param productImgs
     */
    private void addProductImgs(Product product, List<CommonsMultipartFile> productImgs){
        String dest = FileUtil.getShopImgPath(product.getShop().getShopId());
        List<String> imgAddrList = ImageUtil.generateNormalImgs(productImgs, dest);
        if (imgAddrList != null && imgAddrList.size() > 0) {
            List<ProductImg> productImgList = new ArrayList<ProductImg>();
            for (String imgAddr : imgAddrList) {
                ProductImg productImg = new ProductImg();
                productImg.setImgAddr(imgAddr);
                productImg.setProductId(product.getProductId());
                productImg.setCreateTime(new Date());
                productImgList.add(productImg);
            }
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new RuntimeException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new RuntimeException("创建商品详情图片失败:" + e.toString());
            }
        }
    }

    /**
     * 根据商品Id删除商品的所有详情图
     * @param productId
     */
    private void deleteProductImgs(long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            FileUtil.deleteFile(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }
}
