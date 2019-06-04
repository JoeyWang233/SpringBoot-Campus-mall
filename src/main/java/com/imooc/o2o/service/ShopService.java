package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exception.ShopOperationException;

public interface ShopService {
    /** 
    * @Description: 根据shopCondition分页返回响应列表数据 
    * @Param: [shopCondition, pageIndex, pageSize] 
    * @return: com.imooc.o2o.dto.ShopExecution
    * @Author: Joey
    * @Date: 2019/3/5 15:31
    */ 
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    ShopExecution addShop(Shop shop, ImageHolder thumbnail);

    Shop getByShopId(Long shopId);

    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
