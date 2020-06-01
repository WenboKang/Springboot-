package com.exampleofspringboot.demo.utils;

import com.exampleofspringboot.demo.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT 工具类
 * @auther kangwenbo
 * @create 2020-05-27 21:16
 **/
public class JwtUtils {

    public static final String SUBJECT = "xdclass";
    public static final long  EXPIRE = 1000*60*60*24*7;//过期时间：一周

    public static final String  APPSECRET = "xd666";//秘钥


    /**
     * 生成 token
     * @param user
     * @return
     */
    public static  String  geneJsonWebToken(User user){
        if (user==null || user.getId()==null || user.getName()==null || user.getHeadImg()==null){
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET)
                .compact();
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String  token){
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }


}
