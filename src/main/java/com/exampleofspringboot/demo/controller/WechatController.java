package com.exampleofspringboot.demo.controller;

import com.exampleofspringboot.demo.config.WechatConfig;
import com.exampleofspringboot.demo.domain.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @auther kangwenbo
 * @create 2020-05-28 17:39
 **/
@Controller
@RequestMapping("api/v1/wechat")
public class WechatController {

    @Autowired
    private WechatConfig wechatConfig;
    /**
     * 拼装 微信扫一扫登录的 url
     * @return
     */
    @GetMapping("/login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page",required = true) String accessPage) throws UnsupportedEncodingException {
        String redirectURL = wechatConfig.getOpenRedirectUrl(); // 获取开放平台重定向地址
        String callbackURL = URLEncoder.encode(redirectURL,"GBK");//进行编码
        String qrcodeURL = String.format(wechatConfig.getOpenQrcodeUrl(), wechatConfig.getOpenAppid(), callbackURL, accessPage);
        return JsonData.buildSuccess(qrcodeURL);
    }
}
