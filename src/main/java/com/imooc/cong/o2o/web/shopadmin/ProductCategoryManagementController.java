package com.imooc.cong.o2o.web.shopadmin;

import com.imooc.cong.o2o.entity.ProductCategory;
import com.imooc.cong.o2o.service.ProductCategoryService;
import com.imooc.cong.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
@Controller
@RequestMapping("/shop")
public class ProductCategoryManagementController {
    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> productCategoryList(@RequestParam Integer shopId){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            List<ProductCategory> productCategoryList = productCategoryService.getListByShopId(shopId);
            if(productCategoryList == null){
                modelMap.put("success", false);
                modelMap.put("msg", "查询为空");
            }else{
                modelMap.put("success",true);
                modelMap.put("msg","查询成功");
                modelMap.put("productCategoryList", productCategoryList);
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("msg", e.getMessage());
        }
       return modelMap;
    }

    @RequestMapping(value = "addproductcategories", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProductCategory(@RequestBody List<ProductCategory> productCategories){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            int effect = productCategoryService.batchAdd(productCategories);
            if(effect <= 0){
                modelMap.put("success", false);
                modelMap.put("msg", "添加失败");
            }else{
                modelMap.put("success",true);
                modelMap.put("msg", "添加成功");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("msg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeProductCategory(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            int id = HttpServletRequestUtil.getInt(request,"productCategoryId");
            int effect = productCategoryService.delete(id);
            if(effect <= 0){
                modelMap.put("success", false);
                modelMap.put("msg", "删除失败");
            }else{
                modelMap.put("success", true);
                modelMap.put("msg", "删除成功");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("msg", e.getMessage());
        }
        return modelMap;
    }
}
