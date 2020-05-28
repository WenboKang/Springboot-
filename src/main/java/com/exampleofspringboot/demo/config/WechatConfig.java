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

    @Override
    public String toString() {
        return "WechatConfig{" +
                "appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                '}';
    }
}
