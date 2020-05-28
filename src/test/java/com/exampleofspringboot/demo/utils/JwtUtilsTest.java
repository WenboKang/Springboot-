package com.exampleofspringboot.demo.utils;

import com.exampleofspringboot.demo.domain.User;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @auther kangwenbo
 * @create 2020-05-27 21:40
 **/
public class JwtUtilsTest {

    @Test
    public void geneJsonWebToken() {
        User user = new User();
        user.setId(999);
        user.setHeadImg("wwwwwwwwww");
        user.setName("kwb");


        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);

    }

    /**
     * 解密测试
     */
    @Test
    public void checkJWT() {
        String token= "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZGNsYXNzIiwiaWQiOjk5OSwibmFtZSI6Imt3YiIsImltZyI6Ind3d3d3d3d3d3ciLCJpYXQiOjE1OTA2Mjc5MDQsImV4cCI6MTU5MTIzMjcwNH0.8W3Ya1LoLlXPVv82sCFBTrqY3yPVirs3_wuoNDZEYcQ\n";
        Claims claims = JwtUtils.checkJWT(token);
        if (claims!=null){
            String name = (String)claims.get("name");
            int id = (Integer) claims.get("id");
            String img = (String) claims.get("img");
            assertEquals(name , "kwb");
            assertEquals(id , 999);
            assertEquals(img , "wwwwwwwwww");
        }else {
            System.out.println("非法token");
        }
    }
}