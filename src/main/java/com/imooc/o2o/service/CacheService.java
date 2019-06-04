package com.imooc.o2o.service;

public interface CacheService {
    /**
     * 根据key前缀删除与模式匹配的key-value
     * @param keyPrefix
     */
    void removeFromCache(String keyPrefix);
}
