package com.imooc.o2o.entity;

import java.util.Date;

/**
 * @program: oto
 * @description:
 * @author: Joey
 * @create: 2019-02-21 20:38
 */
public class ShopCategory {
    // 主键ID
    private Long shopCategoryId;
    // 类别名
    private String shopCategoryName;
    // 类别描述
    private String shopCategoryDesc;
    // 类别图片地址(针对一级类别)
    private String shopCategoryImg;
    // 权重，越大越排前显示
    private Integer priority;
    // 创建时间
    private Date createTime;
    // 最近的更新时间
    private Date lastEditTime;
    // 父类别
    private ShopCategory parent;

    public ShopCategory() {
    }

    public ShopCategory(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public void setShopCategoryId(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public void setParent(ShopCategory parent) {
        this.parent = parent;
    }

    public Long getShopCategoryId() {

        return shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public ShopCategory getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "ShopCategory{" +
                "shopCategoryId=" + shopCategoryId +
                ", shopCategoryName='" + shopCategoryName + '\'' +
                ", shopCategoryDesc='" + shopCategoryDesc + '\'' +
                ", shopCategoryImg='" + shopCategoryImg + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", parent=" + parent +
                '}';
    }
}
