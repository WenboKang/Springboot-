package com.exampleofspringboot.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @auther kangwenbo   微信配置类
 * @create 2020-05-11 18:43
 **/

@Configuration
@Component
@PropertySource(value = "classpath:application.yml")
public class WechatConfig {
    /*公众号appid
    * */
    @Value("${wxpay.appid}")
    private String appId;
    /*公众号appSecret 秘钥
     * */
    @Value("${wxpay.appsecret}")
    private String appSecret;

    /**
     * 微信开放平台APPID
     */
    @Value("${wxopen.appid}")
    private String openAppid;
    /**
     * 开放平台 APPsecret 秘钥
     */
    @Value("${wxopen.appsecret}")
    private String openAppsecret;
    /**开放平台回调url
     *
     */
    @Value("${wxopen.redirect_url}")
    private  String openRedirectUrl;

    /**
     * 微信开放平台二维码连接
     */
    private final static String OPEN_QRCODE_URL= "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";



    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getOpenAppid() {
        return openAppid;
    }

    public void setOpenAppid(String openAppid) {
        this.openAppid = openAppid;
    }

    public String getOpenAppsecret() {
        return openAppsecret;
    }

    public void setOpenAppsecret(String openAppsecret) {
        this.openAppsecret = openAppsecret;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }

    public   String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }

    @Override
    public String toString() {
        return "WechatConfig{" +
                "appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                '}';
    }
}
