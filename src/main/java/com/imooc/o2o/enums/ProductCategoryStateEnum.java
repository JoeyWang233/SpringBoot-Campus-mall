package com.imooc.o2o.enums;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-03-29 13:52
 */
public enum  ProductCategoryStateEnum {
    SUCCESS(1,"创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY_LIST(-1002,"店铺商品种类小于1");

    private int stateCode;
    private String stateInfo;

    private ProductCategoryStateEnum(int stateCode, String stateInfo) {
        this.stateCode = stateCode;
        this.stateInfo = stateInfo;
    }

    public int getStateCode() {
        return stateCode;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ProductCategoryStateEnum stateOf(int index){
        for(ProductCategoryStateEnum state: values()){
            if(state.getStateCode()==index) {
                return state;
            }
        }
        return null;
    }
}
