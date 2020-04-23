package com.exampleofspringboot.demo.controller;

import com.exampleofspringboot.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName SpringSayHello
 * @Description TODO
 * @Author Y
 * @Date 2019-07-28 16:38
 * @Version 1.0
 **/

@Controller
public class SpringSayHello {

    @RequestMapping(value = "" , method = RequestMethod.GET)
    @ResponseBody
    public String SayHi(){
        return "Hello ShouQianBa";
    }

    @RequestMapping(value={"/index"}, method = RequestMethod.GET)
    public String index(Model model){
        User user = new User();
        user.setUsername("康文博");
        user.setEnglishName("Vince");
        user.setSex("male");
        model.addAttribute("user",user);
        return "index";
    }
    @RequestMapping(value={"/success"})
    public String hello(Map<String , Object> map){
        map.put("hello" , "kangwenbo");
        return "index";
    }

}
