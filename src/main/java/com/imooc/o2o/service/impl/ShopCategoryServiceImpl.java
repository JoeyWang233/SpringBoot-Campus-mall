package com.imooc.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.exception.ShopCategoryOperationException;
import com.imooc.o2o.service.ShopCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    private final ShopCategoryDao shopCategoryDao;
    private final JedisUtil.Keys jedisKeys;
    private final JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

    @Autowired
    public ShopCategoryServiceImpl(ShopCategoryDao shopCategoryDao, JedisUtil.Keys jedisKeys, JedisUtil.Strings jedisStrings) {
        this.shopCategoryDao = shopCategoryDao;
        this.jedisKeys = jedisKeys;
        this.jedisStrings = jedisStrings;
    }

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {

        String key = SCLISTKEY;
        List<ShopCategory> shopCategoryList;
        ObjectMapper mapper = new ObjectMapper();

        /*
          查询条件为空：则列出所有首页大类，即parentId为空的店铺类别，key：shopCategoryList_allfirstlevel
          查询条件不为空，且有parentId：列出该parentId下所有子类别：key: shopCategoryList_parent12
          查询条件不为空，且无parentId，则列出所有子类别 key: shopCategoryList_allsecondlevel
         */
        if (shopCategoryCondition == null) {
            key = key + "_allfirstlevel";
        } else if (shopCategoryCondition.getParent() != null && shopCategoryCondition.getParent().getShopCategoryId() != null) {
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else {
            key = key + "_allsecondlevel";
        }

        if (!jedisKeys.exists(key)) {
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try {
                // 将相关key对应的value里的的string转换成对象的实体类集合
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }
        return shopCategoryList;
    }
}
