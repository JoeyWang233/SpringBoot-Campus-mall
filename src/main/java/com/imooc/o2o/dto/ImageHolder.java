package com.imooc.o2o.dto;

import java.io.InputStream;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-03-31 20:57
 */
public class ImageHolder {

    private String imamgeName;
    private InputStream image;

    public ImageHolder(String imamgeName, InputStream image) {
        this.imamgeName = imamgeName;
        this.image = image;
    }

    public String getImamgeName() {
        return imamgeName;
    }

    public void setImamgeName(String imamgeName) {
        this.imamgeName = imamgeName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
