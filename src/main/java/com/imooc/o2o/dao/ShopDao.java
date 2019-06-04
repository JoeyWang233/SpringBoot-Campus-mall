package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * @Description: 新增店铺
     * @Param: [shop]
     * @return: int
     * @Author: Joey
     * @Date: 2019/2/23 16:47
     */
    int insertShop(Shop shop);
    // 返回值为受影响的行数

    int updateShop(Shop shop);

    Shop queryByShopId(long shopId);

    /** 
    * @Description:  分页查询店铺，可输入的条件有：店铺名(模糊)，店铺状态，店铺类别，区域Id，owner，该5个条件均被封装到一个shop对象中
    * @Param: [shopCondition, rowIndex-从第几行开始取, pageSize-返回的条数]
    * @return: java.util.List<com.imooc.o2o.entity.Shop> 
    * @Author: Joey
    * @Date: 2019/3/1 19:21
    */ 
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /** 
    * @Description: 返回符合查询条件的商铺总数
    * @Param: [shopCondition] 
    * @return: int 
    * @Author: Joey
    * @Date: 2019/3/1 20:39
    */ 
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
