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
import com.imooc.cong.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
@Controller
@RequestMapping("/shop")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //1接收并转换参数
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        MultipartHttpServletRequest multipartRequest = null;
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            multipartRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartRequest
                    .getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }


        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try{
            shop = mapper.readValue(shopStr, Shop.class);
            //注册店铺
            if(shop != null && shopImg != null){
                ShopExecution shopExecution = shopService.addShop(shop, shopImg);
                if(shopExecution.getState() == ShopStateEnum.CHECK.getState()){
                    modelMap.put("success",true);
                    modelMap.put("msg", "注册成功");
                }else{
                    modelMap.put("success", false);
                    modelMap.put("msg", shopExecution.getStateInfo());
                }
            }else {
                modelMap.put("success", false);
                modelMap.put("msg", "店铺信息为空");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("msg:", e.getMessage());
        }
        return modelMap;
    }

    /**
     * 获取店铺注册页面的详细信息：shopCategory列表/area列表
     *
     * @return json
     */
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
}
