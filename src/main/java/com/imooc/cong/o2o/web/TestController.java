package com.imooc.cong.o2o.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.imooc.cong.o2o.entity.Shop;
import com.imooc.cong.o2o.entity.ShopTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author: fangcong
 * @date: 2019/6/10
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/encodingFilter/测试")
    @ResponseBody
    public ModelAndView encodingTest(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inx");
        return modelAndView;
    }

    @RequestMapping("/posttest")
    @ResponseBody
    public String postTest(HttpServletRequest request){
        String s = request.getParameter("shopName");
        Map<String, String[]> map = request.getParameterMap();
        String res = "test : test";
        return res;
    }

    @RequestMapping("/postman")
    @ResponseBody
    public String postmanTest(@RequestBody ShopTest shopTest) throws IOException {
//        String shopStr = (String) map.get("shopStr");
       Map<String, Object> map = shopTest.getShpStr();
        ObjectMapper mapper = new ObjectMapper();
        return "success";
    }

    @RequestMapping("/ajax")
    public String register(){
        return "test/register";
    }

    @RequestMapping("shoplist")
    public String shoplist(){
        return "test/shoplist";
    }
}
