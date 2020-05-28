package com.exampleofspringboot.demo.controller;

import com.exampleofspringboot.demo.domain.Video;
import com.exampleofspringboot.demo.services.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther kangwenbo
 * @create 2020-05-11 20:29
 **/
@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;


    /**
     * 根据ID查找
     * @param videoId
     * @return
     */
    @GetMapping("/findById")
    public Object findById(@RequestParam(value = "video_id",required = true) int videoId){
        return videoService.findById(videoId);
    }

    /**
     * 分页接口
     * @param page 当前页数，默认为1
     * @param pageSize 每一页大小，默认为10
     * @return
     */
    @GetMapping("/page")
    public Object pageVideo(@RequestParam(value = "page",defaultValue = "1")int page,
                            @RequestParam(value = "size",defaultValue = "10")int pageSize){
        //设置分页查询
        PageHelper.startPage(page,pageSize);
        List<Video> all = videoService.findAll();
        PageInfo<Video> pageInfo = new PageInfo<>(all);
        Map<String , Object > dataMap = new HashMap<>();
        dataMap.put("totalSize" , pageInfo.getTotal());//总条数
        dataMap.put("totalPage" , pageInfo.getPages());//总页数
        dataMap.put("currentPage" , page);//当前页数
        dataMap.put("data" , pageInfo.getList());//返回的数据


        return dataMap;
    }

}
