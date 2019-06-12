package com.imooc.cong.o2o.util;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    /**
     * 获取图片的基本路径
     * @return
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/project/o2o/img/";
        }else{
            basePath = "/home/project/o2o/img/";
        }
        return basePath.replace("/", separator);
    }

    /**
     * 获取店铺图片的路径
     * @param shopId 店铺ID
     * @return
     */
    public static String getShopImgPath(long shopId){
        String imagePath = "upload/item/shop/" +shopId+ "/";
        return imagePath.replace("/", separator);
    }
}
