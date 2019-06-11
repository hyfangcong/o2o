package com.imooc.cong.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.cong.o2o.dao.ShopCategoryDao;
import com.imooc.cong.o2o.dto.ShopExecution;
import com.imooc.cong.o2o.entity.Area;
import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.entity.ShopCategory;
import com.imooc.cong.o2o.enums.ShopStateEnum;
import com.imooc.cong.o2o.service.AreaService;
import com.imooc.cong.o2o.service.ShopService;
import com.imooc.cong.o2o.service.impl.AreaServiceImpl;
import com.imooc.cong.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @RequestMapping("/management")
    @ResponseBody
    public Map<String, Object> registerShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //1接收并转换参数
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try{
            shop = mapper.readValue(shopStr, Shop.class);
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg:", e.getMessage());
        }

        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver
                (request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg =(CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }else{
            modelMap.put("succcess", false);
            modelMap.put("errMsg", "上传图片不能为空");
        }
        //注册店铺
        if(shop != null && shopImg != null){
            ShopExecution shopExecution = shopService.addShop(shop, shopImg);
            if(shopExecution.getState() == ShopStateEnum.CHECK.getState()){
                modelMap.put("success",true);
            }else{
                modelMap.put("success", false);
                modelMap.put("errMsg", shopExecution.getStateInfo());
            }
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
        }
        return modelMap;
    }

    @RequestMapping("/getshopinitinfo")
    @ResponseBody
    public Map<String, Object> shopInit(){
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategorys();
        List<Area> areas = areaService.getAreaList();
        modelMap.put("shopCategoryList", shopCategories);
        modelMap.put("areaCategoryList", areas);
        return modelMap;
    }

    @RequestMapping("/registershop")
    @ResponseBody
    public ModelAndView registerPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shop/shopoperation");
        return modelAndView;
    }
}
