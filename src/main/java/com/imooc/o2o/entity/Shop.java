package com.imooc.o2o.entity;

import java.util.Date;

/**
 * @program: oto
 * @description:
 * @author: Joey
 * @create: 2019-02-21 20:46
 */
public class Shop {
    // 主键ID
    private Long shopId;
    // 店铺名称
    private String shopName;
    // 店铺简介
    private String shopDesc;
    // 店铺地址
    private String shopAddr;
    // 店铺号码
    private String phone;
    // 店铺门面图片地址
    private String shopImg;
    // 权重，越大越排前显示
    private Integer priority;
    // 创建时间
    private Date createTime;
    // 最近一次更新的时间
    private Date lastEditTime;
    // -1.不可用 0.审核中 1.可用
    private Integer enableStatus;
    // 超级管理员给店家的提醒，包括为什么审核不通过等
    private String advice;

    // 店铺所属区域
    private Area area;
    // 店铺是属于哪个店家的
    private PersonInfo owner;
    // 店铺类别
    private ShopCategory shopCategory;

    public Shop() {
    }

    public Shop(Long shopId) {
        this.shopId = shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
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

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setOwner(PersonInfo owner) {
        this.owner = owner;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    public Long getShopId() {

        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public String getPhone() {
        return phone;
    }

    public String getShopImg() {
        return shopImg;
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

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public String getAdvice() {
        return advice;
    }

    public Area getArea() {
        return area;
    }

    public PersonInfo getOwner() {
        return owner;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopDesc='" + shopDesc + '\'' +
                ", shopAddr='" + shopAddr + '\'' +
                ", phone='" + phone + '\'' +
                ", shopImg='" + shopImg + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", enableStatus=" + enableStatus +
                ", advice='" + advice + '\'' +
                ", area=" + area +
                ", owner=" + owner +
                ", shopCategory=" + shopCategory +
                '}';
    }
}
