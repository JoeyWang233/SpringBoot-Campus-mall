package com.imooc.o2o.dao;

import com.imooc.o2o.entity.WechatAuth;

public interface WechatAuthDao {
    /** 
    * @Description: 通过openId查询对应本平台的微信账号
    * @Param: [openId] 
    * @return: com.imooc.o2o.entity.WechatAuth 
    * @Author: Joey
    * @Date: 2019/5/29 22:30
    */ 
    WechatAuth queryWechatInfoByOpenId(String openId);
    
    /** 
    * @Description: 添加对应本平台的微信账号 
    * @Param: [wechatAuth] 
    * @return: int 
    * @Author: Joey
    * @Date: 2019/5/29 23:15
    */ 
    int insertWechatAuth(WechatAuth wechatAuth);
}
