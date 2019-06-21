package com.imooc.cong.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.cong.o2o.dto.ProductExecution;
import com.imooc.cong.o2o.entity.Product;
import com.imooc.cong.o2o.entity.ProductCategory;
import com.imooc.cong.o2o.enums.ProductStateEnum;
import com.imooc.cong.o2o.service.ProductCategoryService;
import com.imooc.cong.o2o.service.ProductService;
import com.imooc.cong.o2o.util.CodeUtil;
import com.imooc.cong.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: fangcong
 * @date: 2019/6/18
 */
@Controller
@RequestMapping("/shop")
public class ProductManagementController {
    private static final int IMAGEMAXCOUNT = 6;

    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            if(!CodeUtil.checkVertifyCode(request)){
                modelMap.put("success",false);
                modelMap.put("msg","验证码错误");
                return modelMap;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String productStr = HttpServletRequestUtil.getString(request,"productStr");
            Product product = objectMapper.readValue(productStr, Product.class);
            CommonsMultipartFile smallImg = null;
            List<CommonsMultipartFile> productImgs = new ArrayList<>();
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if(resolver.isMultipart(request)){
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                smallImg = (CommonsMultipartFile) multiRequest.getFile("smallImg");
                for(int i = 0; i < IMAGEMAXCOUNT; i++){
                    CommonsMultipartFile tempImg = (CommonsMultipartFile)multiRequest.getFile("productImg" + i);
                    if(tempImg != null){
                        productImgs.add(tempImg);
                    }
                }
            }

            ProductExecution pe = productService.addProduct(product, smallImg, productImgs);
            if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
                modelMap.put("success", true);
                modelMap.put("msg","添加成功");
            }else{
                modelMap.put("success",false);
                modelMap.put("msg","添加失败");
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("msg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyProduct(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            if (!CodeUtil.checkVertifyCode(request)) {
                modelMap.put("success",false);
                modelMap.put("msg","验证码错误");
                return modelMap;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String productStr = HttpServletRequestUtil.getString(request,"productStr");
            Product product = objectMapper.readValue(productStr, Product.class);
            CommonsMultipartFile smallImg = null;
            List<CommonsMultipartFile> productImgs = new ArrayList<>();
            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if(resolver.isMultipart(request)){
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                smallImg = (CommonsMultipartFile) multiRequest.getFile("smallImg");
                for(int i = 0; i < IMAGEMAXCOUNT; i++){
                    CommonsMultipartFile tempImg = (CommonsMultipartFile)multiRequest.getFile("productImg" + i);
                    if(tempImg != null){
                        productImgs.add(tempImg);
                    }
                }
            }

            ProductExecution pe = productService.modifyProduct(product, smallImg, productImgs);
            if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
                modelMap.put("msg","修改成功");
            }else{
                modelMap.put("success",false);
                modelMap.put("msg","修改失败");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("msg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "changeproductstatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeProductStatus(@RequestBody Product product){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            ProductExecution pe = productService.modifyProduct(product);
            if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
                modelMap.put("success", true);
                modelMap.put("msg", "修改成功");
            }else{
                modelMap.put("success",false);
                modelMap.put("msg","修改失败");
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("msg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/getproductlistbyshopid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> productList(@RequestParam Integer shopId){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            ProductExecution pe = productService.getProductsByShopId(shopId);
            if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
                modelMap.put("msg","查询成功");
                modelMap.put("productList", pe.getProductList());
            }else{
                modelMap.put("success",false);
                modelMap.put("msg","查询失败");
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("msg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProduct(@RequestParam("productId") Integer productId, @RequestParam("shopId") Integer shopId){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            ProductExecution pe = productService.getProductById(productId);
            List<ProductCategory> productCategoryList = productCategoryService.getListByShopId(shopId);
            if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
                modelMap.put("msg","查询成功");
                modelMap.put("product", pe.getProduct());
                modelMap.put("productCategoryList", productCategoryList);
            }else{
                modelMap.put("success",false);
                modelMap.put("msg","查询失败");
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("msg", e.getMessage());
        }
        return modelMap;
    }

}
