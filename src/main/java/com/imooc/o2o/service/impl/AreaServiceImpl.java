package com.imooc.o2o.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dao.AreaDao;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.exception.AreaOperationException;
import com.imooc.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: oto
 * @description:
 * @author: Joey
 * @create: 2019-02-22 20:46
 */
@Service
public class AreaServiceImpl implements AreaService {

    private final AreaDao areaDao;
    private final JedisUtil.Keys jedisKeys;
    private final JedisUtil.Strings jedisStrings;

    public Logger log = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    public AreaServiceImpl(AreaDao areaDao, JedisUtil.Keys jedisKeys, JedisUtil.Strings jedisStrings) {
        this.areaDao = areaDao;
        this.jedisKeys = jedisKeys;
        this.jedisStrings = jedisStrings;
    }

    @Override
    @Transactional
    public List<Area> getAreaList() {
        String key = AREALISTKEY;
        List<Area> areaList;
        ObjectMapper mapper = new ObjectMapper();

        if (!jedisKeys.exists(key)) {
            // 如果redis中不存在该键值对，则从数据库中获取数据，并将数据写入redis
            areaList = areaDao.queryArea();
            try {
                String jsonString = mapper.writeValueAsString(areaList);
                jedisStrings.set(key, jsonString);
            } catch (JsonProcessingException e) {
                log.debug(e.getMessage());
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            }
            return areaList;
        }

        String jsonString = jedisStrings.get(key);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
        try {
            areaList = mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
            throw new AreaOperationException(e.getMessage());
        }

        return areaList;
    }
}
