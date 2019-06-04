package com.imooc.o2o.dto;

/**
 * @program: o2o
 * @description: 封装json对象
 * @author: Joey
 * @create: 2019-03-29 13:09
 */
public class Result<T> {
    private boolean success;
    private T data;
    private String errorMsg;
    private int errorCode;

    public Result(){}

    // 数据获取成功时的 Constructor
    public Result(boolean success, T data){
        this.success = success;
        this.data = data;
    }

    // 数据获取失败时的 Constructor
    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
