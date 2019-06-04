package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exception.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
    * @Description: 将此类别下的商品Id置为空，再删除掉该商品类别
    * @Param: [productCategoryId, shopId]
    * @return: com.imooc.o2o.dto.ProductCategoryExecution
    * @Author: Joey
    * @Date: 2019/3/30 22:36
    */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
