package com.imooc.cong.o2o.service;

import com.imooc.cong.o2o.dto.ProductExecution;
import com.imooc.cong.o2o.entity.Product;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
public interface ProductService {
    /**
     * 根据shopId查询商品商品列表
     * @param shopId shopId
     * @return
     */
    ProductExecution getProductsByShopId(Integer shopId);

    /**
     * 根据productId查询商品信息
     * @param productId productId
     * @return
     */
    ProductExecution getProductById(Integer productId);

    /**
     * 新增商品
     * @param product
     * @return
     */
    ProductExecution addProduct(Product product, CommonsMultipartFile smallImg, List<CommonsMultipartFile> productImgs);

    /**
     * 修改商品信息,包括缩略图，详情图
     * @param product {@link Product}
     * @param file file
     * @return
     */
    ProductExecution modifyProduct(Product product, CommonsMultipartFile smallImg, List<CommonsMultipartFile> productImgs);

    /**
     * 修改商品状态-enableStatus
     * @param product
     * @return
     */
    ProductExecution modifyProduct(Product product);
}
