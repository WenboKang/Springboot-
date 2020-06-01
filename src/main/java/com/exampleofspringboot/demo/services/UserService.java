package com.exampleofspringboot.demo.services;

import com.exampleofspringboot.demo.domain.User;

/**
 * 用户业务接口类
 * @auther kangwenbo
 * @create 2020-05-29 11:55
 **/
public interface UserService {

    public User saveWeChatUser(String code);

}
