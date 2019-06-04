package com.imooc.o2o.exception;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-03-31 20:34
 */
public class ProductOperationException extends RuntimeException {
    public ProductOperationException(String message) {
        super(message);
    }
}
