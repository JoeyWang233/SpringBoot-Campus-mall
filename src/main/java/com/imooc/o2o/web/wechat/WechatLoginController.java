package com.imooc.o2o.web.wechat;

import com.imooc.o2o.dto.UserAccessToken;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.dto.WechatUser;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;
import com.imooc.o2o.service.PersonInfoService;
import com.imooc.o2o.service.WechatAuthService;
import com.imooc.o2o.util.wechat.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize
 * ?appid=您的appId
 * &redirect_uri=http://o2o.yitiaojieinfo.com/o2o/wechatlogin/logincheck
 * &response_type=code 78
 * &scope=snsapi_userinfo
 * &state=1#wechat_redirect
 * 则这里将会获取到code,之后再可以通过code获取到access_token 进而获取到用户信息
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4b1caec8753db412&redirect_uri=http://o2o.wanggaofei.top/o2o/wechatlogin/logincheck&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 */
@Controller
@RequestMapping("wechatlogin")
public class WechatLoginController {

    private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);
    // 上述url中，若state=1,则/logincheck方法返回frontend/index.html;若state=2，返回shopadmin/shoplist.html
    private static final String FRONTEND = "1";
    private static final String SHOPEND = "2";

    private final PersonInfoService personInfoService;

    private final WechatAuthService wechatAuthService;

    @Autowired
    public WechatLoginController(PersonInfoService personInfoService, WechatAuthService wechatAuthService) {
        this.personInfoService = personInfoService;
        this.wechatAuthService = wechatAuthService;
    }

    @RequestMapping(value = "/logincheck", method = {RequestMethod.GET})
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("wechat login get...");

        String code = request.getParameter("code");
        log.debug("wechat login code:" + code);

        String roleType = request.getParameter("state");

        WechatUser user = null;
        String openId = null;
        WechatAuth auth = null;
        PersonInfo personInfo = null;
        if (null != code) {
            UserAccessToken token;
            try {
                token = WechatUtil.getUserAccessToken(code);
                assert token != null;
                log.debug("wechat login token:" + token.toString());

                String accessToken = token.getAccessToken();
                openId = token.getOpenId();
                user = WechatUtil.getUserInfo(accessToken, openId);
                assert user != null;
                log.debug("wechat login user:" + user.toString());

                request.getSession().setAttribute("openId", openId);
                auth = wechatAuthService.getWechatAuthByOpenId(openId);
            } catch (IOException e) {
                log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
                e.printStackTrace();
            }
        }

        if (auth == null) {
            // tb_wechat_auth中没有当前openId对应的记录
            assert user != null;
            personInfo = WechatUtil.getPersonInfoFromRequest(user);

            if (FRONTEND.equals(roleType)) {
                personInfo.setUserType(1);
            } else {
                personInfo.setUserType(2);
            }
            auth = new WechatAuth();
            auth.setOpenId(openId);
            auth.setPersonInfo(personInfo);

            WechatAuthExecution we = wechatAuthService.register(auth);
            if (we.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
                return null;
            }
        }

        // 将当前用户放入session中
        personInfo = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
        request.getSession().setAttribute("user",personInfo);

        if (FRONTEND.equals(roleType)) {
            // 用户点击前端展示系统按钮，进入前端展示系统
            return "frontend/index";
        }else {
            // 店家管理
            return "shop/shoplist";
        }
    }
}
