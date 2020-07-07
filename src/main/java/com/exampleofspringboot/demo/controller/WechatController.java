package com.exampleofspringboot.demo.controller;

import com.exampleofspringboot.demo.config.WechatConfig;
import com.exampleofspringboot.demo.domain.JsonData;
import com.exampleofspringboot.demo.domain.User;
import com.exampleofspringboot.demo.domain.VideoOrder;
import com.exampleofspringboot.demo.exception.MyException;
import com.exampleofspringboot.demo.services.UserService;
import com.exampleofspringboot.demo.services.VideoOrderService;
import com.exampleofspringboot.demo.utils.JwtUtils;
import com.exampleofspringboot.demo.utils.WXPayUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;


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

    @Autowired
    private VideoOrderService videoOrderService;



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

    /**
     * 微信支付回调
     * @param request
     * @param response
     */
    @RequestMapping("/order/callback")
    public void  OrderCallback(HttpServletRequest request , HttpServletResponse response) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        // BufferedReader 是包装设计模式，性能更高
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line ="";
        while ( (line=bufferedReader.readLine()) != null){
            sb.append(line);
        }
        bufferedReader.close();
        inputStream.close();
        Map<String , String > callbackMap = WXPayUtil.xmlToMap(sb.toString());

        SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(callbackMap);

        // 判断签名是否正确
        if (WXPayUtil.isCorrectSign(sortedMap , wechatConfig.getKey())){
            if("SUCCESS".equals(sortedMap.get("result_code"))){
                String outTradeNo = sortedMap.get("out_trade_no");
                VideoOrder videoOrder = videoOrderService.findByOutTradeNo(outTradeNo);
                if (videoOrder.getState()==0){  // 未支付状态
                    VideoOrder newVideoOrder = new VideoOrder();
                    newVideoOrder.setOpenId(sortedMap.get("openid"));
                    newVideoOrder.setOutTradeNo(outTradeNo);
                    newVideoOrder.setNotifyTime(new Date());
                    newVideoOrder.setTotalFee(videoOrder.getTotalFee());
                    newVideoOrder.setState(1);//支付状态
                    newVideoOrder.setIp(videoOrder.getIp());

                    int rows = videoOrderService.updateVideoOrderByOutTradeNo(newVideoOrder);// 影响行数
                    if (rows==1){
                        //通知微信支付成功
                        response.setContentType("text/xml");
                        response.getWriter().println("success");
                    }else{

                    }
                }
            }

        }

        //更新订单状态

        //判断影响行数 row==1 或 row==0 , 响应微信成功或者失败

    }



}
