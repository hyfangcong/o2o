package com.imooc.cong.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class ImageUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static Random random = new Random();

    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(thumbnail.getInputStream())
                    .size(200,200)
                    .toFile(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        return dest.getName();
    }

    /**
     * 创建目标路径下所所涉及的路径
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFilePath = PathUtil.getImgBasePath() + targetAddr;
        File file = new File(realFilePath);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    /**
     * 获取问价流的扩展名
     * @param cFile 文件流
     * @return
     */
    private static String getFileExtension(CommonsMultipartFile cFile) {
        String fileName = cFile.getName();
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名： 当前年月日小时秒 + 5位随机数
     * @return
     */
    private static String getRandomFileName() {
        String date = simpleDateFormat.format(new Date());
        int r = random.nextInt(89999) + 10000;
        return date + r;

    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("E:\\img\\o2o\\1213731.png"))
                .size(200,200)
                .toFile("E:\\img\\o2o\\new1213731.png");
    }
}
