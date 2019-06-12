package com.imooc.cong.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: fangcong
 * @date: 2019/6/12
 */
@Controller
@RequestMapping("/shop")
public class ShopPageController {

    /**
     * 注册新店铺
     *
     * @return shop/register.html
     */
    @RequestMapping("/register")
    public String register(){
        return "shop/register";
    }
}
