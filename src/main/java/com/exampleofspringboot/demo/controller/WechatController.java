package com.exampleofspringboot.demo.controller;

import com.exampleofspringboot.demo.config.WechatConfig;
import com.exampleofspringboot.demo.domain.JsonData;
import com.exampleofspringboot.demo.domain.User;
import com.exampleofspringboot.demo.services.UserService;
import com.exampleofspringboot.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @auther kangwenbo
 * @create 2020-05-28 17:39
 **/
@Controller
@RequestMapping("api/v1/wechat")
public class WechatController {

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private UserService userService ;



    /**
     * 拼装 微信扫一扫登录的 url
     * @return
     */
    @GetMapping("/login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page",required = true) String accessPage) throws UnsupportedEncodingException {
        String redirectURL = wechatConfig.getOpenRedirectUrl(); // 获取开放平台重定向地址
        String callbackURL = URLEncoder.encode(redirectURL,"UTF-8");//进行编码
        String qrcodeURL = String.format(wechatConfig.getOpenQrcodeUrl(), wechatConfig.getOpenAppid(), callbackURL, accessPage);
        return JsonData.buildSuccess(qrcodeURL);
    }

    /**
     * 微信扫码登录回调地址
     * @param code
     * @param state
     * @param response
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code",required = true) String code
                                    , String state , HttpServletResponse response) throws IOException {
        User user = userService.saveWeChatUser(code);
        if (user!=null){
            //生成jwt
            String token = JwtUtils.geneJsonWebToken(user);
            response.sendRedirect(state+"?token="+token+"&head_img"+user.getHeadImg()+"&name"+URLEncoder.encode(user.getName() ,"UTF-8") );
        }

    }

}
