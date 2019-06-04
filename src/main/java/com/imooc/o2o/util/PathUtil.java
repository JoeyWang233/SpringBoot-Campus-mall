package com.imooc.o2o.util;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-02-23 20:52
 */
public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    /**
    * @Description:  获取本地磁盘存放图片的位置的根路径
    * @Param: []
    * @return: java.lang.String
    * @Author: Joey
    * @Date: 2019/2/24 14:17
    */
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath;
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/桌面临时文件/SSM到Spring Boot";
        } else {
            basePath = "/home/o2o";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    /** 
    * @Description: 获取shop图片在image内相对路径 
    * @Param: [shopId] 
    * @return: java.lang.String 
    * @Author: Joey
    * @Date: 2019/2/24 16:40
    */ 
    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/images/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
