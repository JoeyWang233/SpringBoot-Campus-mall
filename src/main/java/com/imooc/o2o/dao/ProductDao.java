package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * @Description: 插入商品
     * @Param: [product]
     * @return: int
     * @Author: Joey
     * @Date: 2019/3/31 15:38
     */
    int insertProduct(Product product);

    /**
     * @Description: 通过productId查询唯一的商品信息
     * @Param: [productId]
     * @return: com.imooc.o2o.entity.Product
     * @Author: Joey
     * @Date: 2019/4/1 21:15
     */
    Product queryProductById(long productId);

    /**
     * @Description: 更新商品信息
     * @Param: [product]
     * @return: int
     * @Author: Joey
     * @Date: 2019/4/1 21:18
     */
    int updateProduct(Product product);

    /**
     * @Description: 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺ID，商品类别
     * @Param: [productCondition]
     * @return: java.util.List<com.imooc.o2o.entity.Product>
     * @Author: Joey
     * @Date: 2019/4/12 11:24
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
    * @Description:  根据条件查询商品数量
    * @Param:
    * @return:
    * @Author: Joey
    * @Date: 2019/4/12 13:00
    */
    int queryProductCount(@Param("productCondition") Product productCondition);
    
    /** 
    * @Description: 删除商品类别之前，将商品类别ID置为空 
    * @Param: [productCategoryId]
    * @return: int 
    * @Author: Joey
    * @Date: 2019/4/13 13:07
    */ 
    int updateProductCategoryToNull(long productCategoryId);

//
//    /**
//    * @Description: 删除指定商品
//    * @Param: [productId]
//    * @return: int
//    * @Author: Joey
//    * @Date: 2019/4/2 21:34
//    */
//    int deleteProductImgByProductId(long productId);
}
