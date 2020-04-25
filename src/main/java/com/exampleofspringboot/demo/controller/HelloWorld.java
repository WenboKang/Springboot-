package com.exampleofspringboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther kangwenbo
 * @create 2020-04-25 18:27
 **/
@Controller
public class HelloWorld {
    @RequestMapping("/hello")
    @ResponseBody
    public String helloJava(){
        return "Hello Java Springboot !!!";
    }
}
