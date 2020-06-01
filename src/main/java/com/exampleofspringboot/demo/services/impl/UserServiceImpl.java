package com.exampleofspringboot.demo.services.impl;

import com.exampleofspringboot.demo.config.WechatConfig;
import com.exampleofspringboot.demo.domain.User;
import com.exampleofspringboot.demo.mapper.UserMapper;
import com.exampleofspringboot.demo.services.UserService;
import com.exampleofspringboot.demo.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @auther kangwenbo
 * @create 2020-05-29 11:57
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    WechatConfig wechatConfig;

    @Autowired
    private UserMapper userMapper ;

    @Override
    public User saveWeChatUser(String code) {
        String accessTokenUrl = String.format(WechatConfig.OPEN_ACCESS_TOKEN_URL, wechatConfig.getOpenAppid(), wechatConfig.getOpenAppsecret(), code);
        // 获取AccessToken
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
        if (baseMap ==null ||baseMap.isEmpty()){
            return null;
        }
        String  accessToken = (String)baseMap.get("accexxToken");
        String  openid = (String)baseMap.get("openid");

        User dbuser = userMapper.findByOpenId(openid); // 从数据库查找
        if (dbuser!=null){//更新用户 或者返回
            return dbuser ;
        }

        // 数据库中查找不到用户信息
        // 获取 用户信息
        String userInfoUrl = String.format(WechatConfig.OPEN_USER_INFO_URL, accessToken, openid);
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
        if (baseUserMap==null || baseUserMap.isEmpty()){
            return null ;
        }
        String  nickname = (String)baseUserMap.get("nickname");
        try {
            nickname = new String(nickname.getBytes("ISO-8859-1"),"UTF-8"); //解决乱码
        }catch (Exception e){
            e.printStackTrace();
        }

        Double  sexTmp = (Double)baseUserMap.get("sex");
        int sex = sexTmp.intValue();
        String  province = (String)baseUserMap.get("province");
        String  city = (String)baseUserMap.get("city");
        String  country = (String)baseUserMap.get("country");
        String  headmgurl = (String)baseUserMap.get("headimgurl");

        StringBuilder  address = new StringBuilder(country).append("||").append(province).append("||").append(city);  //中国||上海||上海
        String finalAddress = address.toString();

        try {
            nickname = new String(nickname.getBytes("ISO-8859-1"),"UTF-8"); //解决乱码
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"),"UTF-8"); //解决乱码
        }catch (Exception e){
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headmgurl);
        user.setCity(finalAddress);
        user.setOpenid(openid);
        user.setSex(sex);
        user.setCreateTime(new Date());

        userMapper.save(user);//保存用户信息
        return user;
    }
}
