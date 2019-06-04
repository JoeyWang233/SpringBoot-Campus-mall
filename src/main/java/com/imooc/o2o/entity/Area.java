package com.imooc.o2o.entity;

import java.util.Date;

/**
 * @program:
 * @description:
 * @author: Joey
 * @create: 2019-02-21 19:25
 */
public class Area {
    private Integer areaId;
    private String areaName;
    // 权重
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;

    public Area() {
    }

    public Area(Integer areaId) {

        this.areaId = areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public Integer getAreaId() {

        return areaId;
    }

    public String getAreaName() {
        return areaName;
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
}
