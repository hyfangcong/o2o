package com.imooc.cong.o2o.service;

import com.imooc.cong.o2o.dto.ShopExecution;
import com.imooc.cong.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public interface ShopService {
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);

}
