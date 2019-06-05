package com.imooc.o2o.web.wechat;

import com.imooc.o2o.util.wechat.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: o2o
 * @description:
 * @author: Joey
 * @create: 2019-04-20 10:29
 */
@Controller
@RequestMapping("wechat")
public class WechatController {
    private static Logger log = LoggerFactory.getLogger(WechatController.class);

    @RequestMapping(method = {RequestMethod.GET})
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("wechat get...");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        // 通过验证signature对请求进行校验，若校验成功则响应原样echostr，表示接入成功，否则接入失败
        try (PrintWriter out = response.getWriter()) {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                log.debug("wechat get success...");
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
