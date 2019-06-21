package com.imooc.cong.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class ImageUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static Random random = new Random();

    /**
     * 创建店铺缩略图
     * @param thumbnail
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(thumbnail.getInputStream())
                    .size(200,200)
                    .toFile(dest);
        }catch (IOException e){
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return dest.getName();
    }

//    /**
//     * 创建商品缩略图
//     * @param thumbnail
//     * @param targetAddr
//     * @return
//     */
//    public static String generateNormalImg(CommonsMultipartFile thumbnail, String targetAddr) {
//        String realFileName = getRandomFileName();
//        String extension = getFileExtension(thumbnail);
//        makeDirPath(targetAddr);
//        String relativeAddr = targetAddr + realFileName + extension;
//        File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
//        try {
//            Thumbnails.of(thumbnail.getInputStream()).size(337, 640).outputQuality(0.5f).toFile(dest);
//        } catch (IOException e) {
//            throw new RuntimeException("创建缩略图失败：" + e.toString());
//        }
//        return relativeAddr;
//    }


    /**
     * 创建详情图
     * @param imgs
     * @param targetAddr
     * @return
     */
    public static List<String> generateNormalImgs(List<CommonsMultipartFile> imgs, String targetAddr) {
        int count = 0;
        List<String> relativeAddrList = new ArrayList<String>();
        if (imgs != null && imgs.size() > 0) {
            makeDirPath(targetAddr);
            for (CommonsMultipartFile img : imgs) {
                String realFileName = getRandomFileName();
                String extension = getFileExtension(img);
                String relativeAddr = targetAddr + realFileName + count + extension;
                File dest = new File( FileUtil.getImgBasePath() + relativeAddr);
                count++;
                try {
                    Thumbnails.of(img.getInputStream()).size(600, 300).outputQuality(0.5f).toFile(dest);
                } catch (IOException e) {
                    throw new RuntimeException("创建详情图失败：" + e.toString());
                }
                relativeAddrList.add(relativeAddr);
            }
        }
        return relativeAddrList;
    }

    /**
     * 创建目标路径下所所涉及的路径
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFilePath = FileUtil.getImgBasePath() + targetAddr;
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
        String fileName = cFile.getOriginalFilename();
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
