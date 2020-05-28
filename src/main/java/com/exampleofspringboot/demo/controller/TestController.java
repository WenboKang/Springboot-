package com.exampleofspringboot.demo.controller;

import com.exampleofspringboot.demo.config.WechatConfig;
import com.exampleofspringboot.demo.domain.JsonData;
import com.exampleofspringboot.demo.domain.Video;
import com.exampleofspringboot.demo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther kangwenbo
 * @create 2020-04-25 18:27
 **/
@Controller
public class TestController {

    // 注入 微信配置类
    @Autowired
    private WechatConfig wechatConfig;
    @RequestMapping("/testConfig")
    @ResponseBody
    public JsonData testConfig(){

        //return "id:  "+wechatConfig.getAppId()+"\t"+"秘钥：   "+wechatConfig.getAppSecret();
        return JsonData.buildSuccess(wechatConfig.getAppId());
    }


    @Autowired
    private VideoMapper videoMapper;

    /*video查找所有*/
    @RequestMapping("/testDB")
    @ResponseBody
    public Object testDB(){
        List<Video> all = videoMapper.findAll();
        return all.toString();
    }

}
