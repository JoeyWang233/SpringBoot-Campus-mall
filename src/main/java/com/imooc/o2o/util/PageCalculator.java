package com.imooc.o2o.util;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-03-05 15:24
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
