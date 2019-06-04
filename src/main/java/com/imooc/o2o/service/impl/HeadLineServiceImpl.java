package com.imooc.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dao.HeadLineDao;
import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.exception.AreaOperationException;
import com.imooc.o2o.exception.HeadLineOperationException;
import com.imooc.o2o.service.HeadLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-04-16 09:34
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {

    private final HeadLineDao headLineDao;
    private final JedisUtil.Keys jedisKeys;
    private final JedisUtil.Strings jedisStrings;

    public Logger log = LoggerFactory.getLogger(HeadLineServiceImpl.class);

    @Autowired
    public HeadLineServiceImpl(HeadLineDao headLineDao, JedisUtil.Keys jedisKeys, JedisUtil.Strings jedisStrings) {
        this.headLineDao = headLineDao;
        this.jedisKeys = jedisKeys;
        this.jedisStrings = jedisStrings;
    }

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        String key = HLLISTKEY;
        List<HeadLine> headLineList;
        ObjectMapper mapper = new ObjectMapper();

        // 前台传入的headLineCondition分为三种：null or headline.enableStatus=0 or =1
        // redis中有两种key: headlinelist_0、headlinelist_1
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
            key = key + "_" + headLineCondition.getEnableStatus();
        }

        if (!jedisKeys.exists(key)) {
            // 如果redis中不存在该键值对，则从数据库中获取数据，并将数据写入redis
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            try {
                String jsonString = mapper.writeValueAsString(headLineList);
                jedisStrings.set(key, jsonString);
            } catch (JsonProcessingException e) {
                log.debug(e.getMessage());
                e.printStackTrace();
                throw new HeadLineOperationException(e.getMessage());
            }
            return headLineList;
        }

        String jsonString = jedisStrings.get(key);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
        try {
            headLineList = mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
            throw new AreaOperationException(e.getMessage());
        }

        return headLineList;
    }
}
