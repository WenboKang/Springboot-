package com.exampleofspringboot.demo.controller;

import com.exampleofspringboot.demo.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口
 * @auther kangwenbo
 * @create 2020-06-01 11:48
 **/
@RestController
@RequestMapping("/user/api/v1/order")
public class OrderController {

    @GetMapping("/add")
    public JsonData saveOrder(){
        return JsonData.buildSuccess("下单成功");
    }

}
