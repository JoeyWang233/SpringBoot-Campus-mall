package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {

    /**
    * @Description: 通过shop id查询店铺商品类别
    * @Param:
    * @return:
    * @Author: Joey
    * @Date: 2019/3/29 10:20
    */
    // 一个参数时不需"@Param()"修饰
    List<ProductCategory> queryProductCategoryList(Long shopId);

    /** 
    * @Description: 批量新增商品类别 
    * @Param: [productCategoryList] 
    * @return: int 
    * @Author: Joey
    * @Date: 2019/3/30 14:21
    */ 
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /** 
    * @Description: 根据productCategoryId 删除产品类别
    * @Param: [productCategoryId] 
    * @return: int 
    * @Author: Joey
    * @Date: 2019/3/30 22:07
    */ 
    int deleteProductCategory(@Param("productCategoryId") Long productCategoryId, @Param("shopId") Long shopId);
}
