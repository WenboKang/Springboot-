package com.exampleofspringboot.demo.mapper;

import com.exampleofspringboot.demo.domain.VideoOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @auther kangwenbo
 * @create 2020-06-01 17:16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoOrderMapperTest {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Test
    public void insert() {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setIp("testIP");
        videoOrder.setOpenId( "testOpenid");
        videoOrder.setOutTradeNo("testOutTradeNo");
        videoOrder.setState(0);
        videoOrder.setCreateTime(new Date());
        videoOrder.setNotifyTime(new Date());
        videoOrder.setTotalFee(new Integer(100));
        videoOrder.setNickname("test");
        videoOrder.setHeadImg("this is a img");
        videoOrder.setVideoId(001);
        videoOrder.setVideoTitle("视频名称");
        videoOrder.setVideoImg("视频图片");
        videoOrder.setUserId(9527);
        videoOrder.setDel(0);


        int id = videoOrderMapper.insert(videoOrder);
    }

    @Test
    public void findById() {
        VideoOrder videoOrder = videoOrderMapper.findById(1);
        assertEquals(videoOrder.getNickname(),"哈哈哈");
        assertEquals(videoOrder.getOpenId(),"222");
        assertEquals(videoOrder.getId(),new Integer(1));
    }

    @Test
    public void findByOutTradeNo() {
        VideoOrder videoOrder = videoOrderMapper.findByOutTradeNo("333");
        assertEquals(videoOrder.getNickname(),"哈哈哈");
        assertEquals(videoOrder.getOpenId(),"222");
        assertEquals(videoOrder.getId(),new Integer(1));
    }

    @Test
    public void del() {
    }

    @Test
    public void findMyOrderList() {
    }

    @Test
    public void updateVideoOrderByoutTradeNo() {
    }
}