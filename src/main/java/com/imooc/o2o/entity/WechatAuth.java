package com.imooc.o2o.entity;

import java.util.Date;

/**
 * @program: oto
 * @description: 微信账号Entity
 * @author: Joey
 * @create: 2019-02-21 20:01
 */
public class WechatAuth {
    private Long wechatAuthId;
    private String openId;
    private Date createTime;

    private PersonInfo personInfo;

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public Long getWechatAuthId() {

        return wechatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }
}
