package com.imooc.cong.o2o.dao;

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
public class AreaDaoTest extends BaseTest {
    @Autowired
    AreaDao areaDao;
    @Test
    public void queryAreas(){
        List<Area> areas = areaDao.queryAreas();
        Assert.assertEquals(areas.size(), 3);
    }
}
