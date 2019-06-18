package com.imooc.cong.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.cong.o2o.dao.ShopCategoryDao;
import com.imooc.cong.o2o.dto.ShopExecution;
import com.imooc.cong.o2o.entity.*;
import com.imooc.cong.o2o.enums.ShopStateEnum;
import com.imooc.cong.o2o.service.AreaService;
import com.imooc.cong.o2o.service.ShopService;
import com.imooc.cong.o2o.util.CodeUtil;
import com.imooc.cong.o2o.util.HttpServletRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private static final Logger logger = LoggerFactory.getLogger(ShopManagementController.class);

    @Autowired
    private ShopService shopService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    /**
     * 注册一个新店铺
     * @param request
     * @return
     */
    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        if(!CodeUtil.checkVertifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("msg","验证码输入有误，请重新输入");
            return modelMap;
        }
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
            modelMap.put("msg", "上传图片不能为空");
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

    /**
     * 获取店铺编辑页面的详细信息
     * @param shopId shopId not null
     * @return map
     */
    @RequestMapping(value = {"/getshopbyid"}, method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopById(@RequestParam Integer shopId){
        Map<String, Object> modelMap = new HashMap<>();
        ShopExecution shopExecution = shopService.getByShopId(shopId);
        List<Area> areaList = areaService.getAreaList();
        if(shopExecution.getState() == ShopStateEnum.SUCCESS.getState()){
            modelMap.put("success", true);
            modelMap.put("msg","查询成功");
            modelMap.put("shop", shopExecution.getShop());
            modelMap.put("areaList", areaList);
        }else{
            modelMap.put("success", false);
            modelMap.put("msg", "查询失败");
        }
        return modelMap;
    }

    /**
     * 修改店铺
     * @param request shopId不能为null
     * @return
     */
    @RequestMapping(value = "/doedit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        if(!CodeUtil.checkVertifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("msg","验证码输入有误，请重新输入");
            return modelMap;
        }
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
        }

        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;

        try{
            shop = mapper.readValue(shopStr, Shop.class);
            //修改店铺
            if(shop != null && shop.getShopId() != null){
                ShopExecution shopExecution = shopService.modifyShop(shop, shopImg);
                if(shopExecution.getState() == ShopStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                    modelMap.put("msg", "修改成功");
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

    @RequestMapping(value = "getshoplist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("test");
        request.getSession().setAttribute("user",user);
        user = (PersonInfo) request.getSession().getAttribute("user");
        ShopCondition shopCondition = new ShopCondition();
        shopCondition.setOwnerId(user.getUserId().intValue());
        try {
            ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
            if (shopExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
                modelMap.put("msg", "查询成功");
                modelMap.put("shopList", shopExecution.getShopList());
                modelMap.put("user",user);
            } else {
                modelMap.put("success", false);
                modelMap.put("msg", "查询失败");
            }

        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("msg", e.getMessage());
            logger.error("error:{}",e);

        }
        return modelMap;
    }

    @RequestMapping("/getShopManagementInfo")
    @ResponseBody
    public Map<String,Object>getShopManagementInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if(shopId <= 0){
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if(currentShopObj == null){
                modelMap.put("redirect",true);
                modelMap.put("url","o2o/shop/shoplist");
            }else{
                Shop currentShop = (Shop)currentShopObj;
                modelMap.put("redirect", false);
                modelMap.put("shopId",currentShop.getShopId());
            }
        }else{
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop",currentShop);
            modelMap.put("redirect", false);
        }
        return modelMap;
    }

    public Map<String, Object>getShopCount(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            String shopConditionStr = HttpServletRequestUtil.getString(request, "shopConditionStr");
            ObjectMapper objectMapper = new ObjectMapper();
            ShopCondition shopCondition = objectMapper.readValue(shopConditionStr, ShopCondition.class);
            int count = shopService.shopCount(shopCondition);
            modelMap.put("success", true);
            modelMap.put("msg", "操作成功");
            modelMap.put("shopCount", count);
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("msg", e.getMessage());
        }
        return modelMap;
    }
}
