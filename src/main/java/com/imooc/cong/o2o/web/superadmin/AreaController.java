package com.imooc.cong.o2o.web.superadmin;

import com.imooc.cong.o2o.entity.Area;
import com.imooc.cong.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: fangcong
 * @date: 2019/6/7
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    AreaService areaService;

    @RequestMapping(value = "/arealist" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAreaList(){
        logger.info("============start==========");
        long startTimee = System.currentTimeMillis();
        Map<String, Object> mapModel = new HashMap<>();
        List<Area> areaList ;
        try{
            areaList = areaService.getAreaList();
            mapModel.put("rows", areaList);
            mapModel.put("total", areaList.size());
        }
        catch (Exception e){
            mapModel.put("success", false);
            mapModel.put("errorMsg", e.toString());
        }

        logger.error("errorTest");
        long endTime = System.currentTimeMillis();
        logger.debug("costTime:[{}ms]", endTime - startTimee);
        logger.info("=========end========");
        return mapModel;
    }
}
