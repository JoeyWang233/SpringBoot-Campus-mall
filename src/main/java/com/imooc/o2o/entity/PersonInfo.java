package com.imooc.o2o.entity;

import java.util.Date;

/**
 * @program: oto
 * @description:
 * @author: Joey
 * @create: 2019-02-21 19:43
 */
public class PersonInfo {

    private Long userId;
    private String name;
    private String profileImg;
    private String email;
    private String gender;
    // 0:禁止使用本商城，1：允许使用本商城
    private Integer enableStatus;
    // 1.顾客  2.店家   3.超级管理员
    private Integer userType;
    private Date createTime;
    private Date lastEditTime;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Long getUserId() {

        return userId;
    }

    public String getName() {
        return name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }
}
