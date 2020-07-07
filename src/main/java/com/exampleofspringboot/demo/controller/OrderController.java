package com.exampleofspringboot.demo.controller;

import com.exampleofspringboot.demo.domain.JsonData;
import com.exampleofspringboot.demo.domain.VideoOrder;
import com.exampleofspringboot.demo.dto.VideoOrderDto;
import com.exampleofspringboot.demo.services.VideoOrderService;
import com.exampleofspringboot.demo.utils.IpUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 订单接口
 * @auther kangwenbo
 * @create 2020-06-01 11:48
 **/
@RestController
@RequestMapping("/user/api/v1/order")
//@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private VideoOrderService videoOrderService ;

    @GetMapping("/add")
    public JsonData saveOrder(@RequestParam(value = "video_id", required = true) int videoId
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取用户的ip
        String ip = IpUtils.getIpAddr(request);
        //获取用户的uderID
       int userId = (int)request.getAttribute("user_id");
        //int userId =1;

        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);//--------
        String codeUrl = videoOrderService.save(videoOrderDto);
        if (codeUrl == null){
            throw new  NullPointerException();
        }

        try {
            /*生成二维码*/
            HashMap<EncodeHintType, Object> hints = new HashMap<>();    // 二维码的配置
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);//设置纠错等级
            hints.put(EncodeHintType.CHARACTER_SET , "UTF-8");  //设置编码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 400, 400);//二维码矩阵
            ServletOutputStream outputStream = response.getOutputStream();  //输出流
            MatrixToImageWriter.writeToStream(bitMatrix , "png" , outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }


        return JsonData.buildSuccess("下单成功");
    }

}
