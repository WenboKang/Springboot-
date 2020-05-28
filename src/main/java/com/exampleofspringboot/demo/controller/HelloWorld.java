package com.exampleofspringboot.demo.controller;

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
public class HelloWorld {
    @RequestMapping("/hello")
    @ResponseBody
    public String helloJava(){
        return "Hello    Springboot !!!";
    }




}
