package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.PersonInfoDao;
import com.imooc.o2o.dao.WechatAuthDao;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;
import com.imooc.o2o.exception.WechatAuthOperationException;
import com.imooc.o2o.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {
    private final WechatAuthDao wechatAuthDao;
    private final PersonInfoDao personInfoDao;

    private Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    @Autowired
    public WechatAuthServiceImpl(WechatAuthDao wechatAuthDao, PersonInfoDao personInfoDao) {
        this.wechatAuthDao = wechatAuthDao;
        this.personInfoDao = personInfoDao;
    }

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    @Transactional
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
        // 空值判断
        if (wechatAuth == null || wechatAuth.getOpenId() == null) {
            return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
        }

        try {
            wechatAuth.setCreateTime(new Date());
            // 微信账号夹带着用户信息(openId)并且用户Id为空，则认为该用户第一次使用平台（且通过微信登录）,则自动创建用户信息
            if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectedNum = personInfoDao.insertPersonInfo(personInfo);
                    wechatAuth.setPersonInfo(personInfo);
                    if(effectedNum<=0){
                        throw new WechatAuthOperationException("添加用户信息失败");
                    }
                }catch (Exception e){
                    log.error("insertPersonInfo error:" + e.toString());
                    throw new WechatAuthOperationException("insertPersonInfo error:" + e.getMessage());
                }
            }

            // 创建专属于本平台的微信账号
            int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
            if(effectedNum<=0){
                throw new WechatAuthOperationException("账号创建失败");
            }else {
                return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS,wechatAuth);
            }
        }catch (Exception e){
            log.error("insertWechatAuth error:" + e.toString());
            throw new WechatAuthOperationException("insertWechatAuth error:" + e.getMessage());
        }
    }
}
