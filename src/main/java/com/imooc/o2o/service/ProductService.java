package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exception.ProductOperationException;

import java.util.List;

public interface ProductService {
    /**
     * @Description: 添加商品信息及缩略图处理
     * @Param: []
     * @return: com.imooc.o2o.dto.ProductExecution
     * @Author: Joey
     * @Date: 2019/3/31 20:35
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;

    /**
     * @Description: 根据商品 id 获取商品
     * @Param: [productId]
     * @return: com.imooc.o2o.entity.Product
     * @Author: Joey
     * @Date: 2019/4/2 22:00
     */
    Product getProductById(long productId);

    /**
     * @Description: 修改商品信息及图片处理
     * @Param: [product, thumbnail, productImgList]
     * @return: com.imooc.o2o.dto.ProductExecution
     * @Author: Joey
     * @Date: 2019/4/2 22:06
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;

    /**
    * @Description:  查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺ID，商品类别
    * @Param: [productCondition, pageIndex, pageSize]
    * @return: com.imooc.o2o.dto.ProductExecution
    * @Author: Joey
    * @Date: 2019/4/12 14:54
    */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

}
