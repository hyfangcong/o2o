package com.imooc.cong.o2o.util;

import java.io.File;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class FileUtil {
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

    /**
     * 删除图片
     * @param storePath
     */
    public static void deleteFile(String storePath) {
        File file = new File(getImgBasePath() + storePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            file.delete();
        }
    }
}
