package com.exampleofspringboot.demo.services;

import com.exampleofspringboot.demo.domain.VideoOrder;
import com.exampleofspringboot.demo.dto.VideoOrderDto;
import org.apache.ibatis.annotations.Param;

/**
 * 订单接口
 * @auther kangwenbo
 * @create 2020-06-03 17:00
 **/
public interface VideoOrderService {
    /**
     * 下单接口
     * @param videoOrderDto
     * @return
     */
    String save(VideoOrderDto videoOrderDto) throws Exception;

    /**
     * 根据流水号查找订单
     * @param outTradeNo 订单流水号
     * @return
     */
    VideoOrder findByOutTradeNo( String outTradeNo);

    /**
     * 根据流水号跟新订单
     * @param videoOrder
     * @return
     */
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);


}
