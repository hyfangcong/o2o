package com.imooc.cong.o2o.service.impl;

import com.imooc.cong.o2o.dao.AreaDao;
import com.imooc.cong.o2o.entity.Area;
import com.imooc.cong.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/7
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        List<Area> areas = areaDao.queryAreas();
        return areas;
    }
}
