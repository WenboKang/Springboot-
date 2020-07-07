package com.exampleofspringboot.demo.services.impl;

import com.exampleofspringboot.demo.config.WechatConfig;
import com.exampleofspringboot.demo.domain.User;
import com.exampleofspringboot.demo.domain.Video;
import com.exampleofspringboot.demo.domain.VideoOrder;
import com.exampleofspringboot.demo.dto.VideoOrderDto;
import com.exampleofspringboot.demo.mapper.UserMapper;
import com.exampleofspringboot.demo.mapper.VideoMapper;
import com.exampleofspringboot.demo.mapper.VideoOrderMapper;
import com.exampleofspringboot.demo.services.VideoOrderService;
import com.exampleofspringboot.demo.utils.CommonUtils;
import com.exampleofspringboot.demo.utils.HttpUtils;
import com.exampleofspringboot.demo.utils.WXPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.VariableElement;
import java.util.*;

/**
 * @auther kangwenbo
 * @create 2020-06-03 17:03
 **/
@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    private VideoOrderMapper videoOrderMapper ;
    @Autowired
    private VideoMapper videoMapper ;
    @Autowired
    private UserMapper userMapper ;
    @Autowired
    WechatConfig wechatConfig;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String save(VideoOrderDto videoOrderDto) throws Exception {
        //查找视频信息
        Video video = videoMapper.findById(videoOrderDto.getVideoId());
        //查找用户信息
        User user = userMapper.findById(videoOrderDto.getUserId());

        /*构造订单*/
        VideoOrder videoOrder = new VideoOrder();//订单
            videoOrder.setOutTradeNo(CommonUtils.generateUUID());   //生成流水号
            videoOrder.setTotalFee(video.getPrice());
            videoOrder.setVideoImg(video.getCoverImg());
            videoOrder.setUserId(user.getId());
            videoOrder.setVideoTitle(video.getTitle());
            videoOrder.setCreateTime(new Date());
            videoOrder.setState(0);

            videoOrder.setHeadImg(user.getHeadImg());
            videoOrder.setNickname(user.getName());
            videoOrder.setDel(0);
            videoOrder.setIp(videoOrderDto.getIp());
            videoOrder.setVideoId(video.getId());
        videoOrderMapper.insert(videoOrder);    //保存订单

        /*统一下单，获取code_url*/
        String codeUrl = unifiedOrder(videoOrder);
        return codeUrl;
    }

    @Override
    public VideoOrder findByOutTradeNo(String outTradeNo) {
        VideoOrder videoOrder = videoOrderMapper.findByOutTradeNo(outTradeNo);
        return videoOrder;

    }

    @Override
    public int updateVideoOrderByOutTradeNo(VideoOrder videoOrder) {
        return videoOrderMapper.updateVideoOrderByOutTradeNo(videoOrder);
    }


    /**
     * 统一下单方法
     * @param videoOrder
     * @return
     */
    private String unifiedOrder(VideoOrder videoOrder) throws Exception {
        SortedMap<String , String> params = new TreeMap<>();    //参数集合
        params.put("appid", wechatConfig.getAppId());   //微信公众号的APPID
        params.put("mch_id",wechatConfig.getMerId());   //商户号id
        params.put("nonce_str",CommonUtils.generateUUID()); //一个随机字符
        params.put("out_trade_no",videoOrder.getOutTradeNo());//订单号
        params.put("tatal_fee",videoOrder.getTotalFee().toString());    //费用金额
        params.put("spbill_create_ip",videoOrder.getIp());  //ip
        params.put("notify_url" , wechatConfig.getOpenRedirectUrl());    // 回调通知地址
        params.put("trade_type" , "NATIVE");    //扫码支付
        //生成签名   ， 字典排序 + 盐
        String sign = WXPayUtil.createSign(params, wechatConfig.getKey());
        params.put("sign" , sign);

        //参数 转 xml
        String mapToXml = WXPayUtil.mapToXml(params);

        //微信 统一下单
        String orderStr = HttpUtils.doPost(wechatConfig.UNIFIED_ORDER_URL,mapToXml, 4000);   //这里会有空指针
        if (null==orderStr){
            return null;
        }
        Map<String, String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
//        System.out.println(unifiedOrderMap.toString());
        if(unifiedOrderMap!=null){
            return unifiedOrderMap.get("code_url");
        }

        return null ;

    }
}
