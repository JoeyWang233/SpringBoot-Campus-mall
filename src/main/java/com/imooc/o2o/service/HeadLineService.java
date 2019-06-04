package com.imooc.o2o.service;

import com.imooc.o2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

public interface HeadLineService {

    String HLLISTKEY = "headlinelist";
    /** 
    * @Description: return headline list according to the condition
    * @Param: [headLineCondition] 
    * @return: java.util.List<com.imooc.o2o.entity.HeadLine> 
    * @Author: Joey
    * @Date: 2019/4/16 9:32
    */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
