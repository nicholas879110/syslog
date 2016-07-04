package com.gome.log.entity;


import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;

/**
 * Created by zhangliewei on 2016/6/8.
 */
public class SystemLog implements Serializable{

    private String id;
    private String biz;//业务
    private String clazz;//类
    private String method;//方法
    private String params; //参数
    private String returnObj;//返回结果
    private String exception;//异常
    private Date startDate;//开始时间
    private Date endDate;//结束时间
    private long wasteTime;//时间消耗

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(String returnObj) {
        this.returnObj = returnObj;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getWasteTime() {
        return wasteTime;
    }

    public void setWasteTime(long wasteTime) {
        this.wasteTime = wasteTime;
    }
}
