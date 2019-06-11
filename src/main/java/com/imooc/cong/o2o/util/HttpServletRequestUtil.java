package com.imooc.cong.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key){
        try{
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            return  -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key){
        try{
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key){
        try{
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public static String getString (HttpServletRequest request, String key){
        try{
            String value = request.getParameter(key);
            if(value != null){
                value = value.trim();
            }
            if("".equals(value)){
               value = null;
            }
            return value;
        }catch (Exception e){
            return null;
        }
    }

}
