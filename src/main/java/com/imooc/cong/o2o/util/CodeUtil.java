package com.imooc.cong.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: fangcong
 * @date: 2019/6/12
 */

/**
 * 检查验证码是否正确
 */
public class CodeUtil {
    public static boolean checkVertifyCode(HttpServletRequest request){
        String expected = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String actual = HttpServletRequestUtil.getString(request, "verifyCode");

        if(actual == null || !actual.equals(expected))
            return false;
        else
            return true;
    }
}
