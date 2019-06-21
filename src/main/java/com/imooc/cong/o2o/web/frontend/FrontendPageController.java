package com.imooc.cong.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: fangcong
 * @date: 2019/6/21
 */
@Controller
@RequestMapping(value = "/frontend", method = RequestMethod.GET)
public class FrontendPageController {
    @RequestMapping("index")
    public String index(){
        return "frontend/index";
    }
}
