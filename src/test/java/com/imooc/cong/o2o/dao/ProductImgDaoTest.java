package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.BaseTest;
import com.imooc.cong.o2o.entity.ProductImg;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/19
 */
public class ProductImgDaoTest extends BaseTest {
    @Autowired
    ProductImgDao productImgDao;

    @Test
    public void queryProductImgByIdTest(){
        ProductImg productImg = productImgDao.queryProductImgById(11l);
    }

    @Test
    public void batchInsertProductImgTest(){
        List<ProductImg> productImgDaoList = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr("图片" + i);
            productImg.setProductId(1l);
            productImg.setCreateTime(new Date());
            productImg.setPriority(10);
            productImgDaoList.add(productImg);
        }
        int effect = productImgDao.batchInsertProductImg(productImgDaoList);
        Assert.assertEquals(5,effect);
    }

    @Test
    public void queryProductImgListTest(){
        List<ProductImg> productImgList = productImgDao.queryProductImgList(1);
        Assert.assertEquals(4,productImgList.size());
    }

    @Test
    public void deleteProductImgByProductId(){
        List<ProductImg> productImgList = productImgDao.queryProductImgList(1);
        int effect = productImgDao.deleteProductImgByProductId(1);
        Assert.assertEquals(productImgList.size(),effect);
    }

    @Test
    public void deleteProductImgByProductImgIdTest(){
        int effect = productImgDao.deleteProductImgByProductImgId(11);
        Assert.assertEquals(1,effect);
    }
}
