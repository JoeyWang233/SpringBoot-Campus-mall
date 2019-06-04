package com.imooc.o2o.exception;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-02-24 16:45
 */
public class ShopOperationException extends RuntimeException {
    /**
* @Description:  之所以做一层浅封装是因为ShopOperationException vs RuntimeException更有意义.且异常方便定位
* @Param: [msg]
* @return:
* @Author: Joey
* @Date:
*/
    public ShopOperationException(String msg) {
        super(msg);
    }
}
