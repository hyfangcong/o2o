package com.imooc.cong.o2o.service;

import com.imooc.cong.o2o.BaseTest;
import com.imooc.cong.o2o.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/7
 */
public class AreaServiceTest extends BaseTest {
    @Autowired
    AreaService areaService;

    @Test
    public void areaServiceTest(){
        List<Area> areas =areaService.getAreaList();
        Assert.assertEquals(areas.size(), 3);
    }
}
