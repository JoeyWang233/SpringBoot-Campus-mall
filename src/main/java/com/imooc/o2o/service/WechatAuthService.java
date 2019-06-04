package com.imooc.o2o.service;

import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.exception.WechatAuthOperationException;

public interface WechatAuthService {
    /**
    * @Description: 通过openId查找平台对应的微信账号
    * @Param: [openId]
    * @return: com.imooc.o2o.entity.WechatAuth
    * @Author: Joey
    * @Date: 2019/5/29 22:54
    */
    WechatAuth getWechatAuthByOpenId(String openId);

    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
