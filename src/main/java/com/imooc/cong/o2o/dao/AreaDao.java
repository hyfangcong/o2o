package com.imooc.cong.o2o.dao;

import com.imooc.cong.o2o.entity.Area;

import java.util.List;

/**
 * @author: fangcong
 * @date: 2019/6/7
 */
public interface AreaDao {
    /**
     * 查询所有的区域，并且按照priority降序排列
     * @return
     */
    List<Area> queryAreas();
}
