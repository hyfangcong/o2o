package com.imooc.cong.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "shop/register";
    }


    /**
     * 修改店铺信息
     * @return shop/edit.html
     */
    @RequestMapping(value = "/edit" , method = RequestMethod.GET)
    public String edit(){
        return "shop/edit";
    }

    /**
     * 获取店铺列表
     * @return
     */
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public String shopList(){
        return "shop/shoplist";
    }

    /**
     * 管理店铺页面
     * @return
     */
    @RequestMapping(value = "shopmanage", method = RequestMethod.GET)
    public String shopManage(){
        return "shop/shopmanage";
    }

    /**
     * 商品类别管理页面
     * @return
     */
    @RequestMapping(value = "productcategorymanage", method = RequestMethod.GET)
    public String productCategoryManage(){
        return "shop/productcategorymanage";
    }

    /**
     * 商品管理页面
     * @return
     */
    @RequestMapping(value = "productmanage", method = RequestMethod.GET)
    public String productmanage(){
        return "shop/productmanage";
    }

    /**
     * 商品编辑页面
     * @return
     */
    @RequestMapping(value = "productedit", method = RequestMethod.GET)
    public String productedit(){
        return "shop/productedit";
    }
}
