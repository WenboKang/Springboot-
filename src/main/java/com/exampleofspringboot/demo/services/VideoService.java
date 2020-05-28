package com.exampleofspringboot.demo.services;

import com.exampleofspringboot.demo.domain.Video;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther kangwenbo
 * @create 2020-05-11 20:06
 **/
/*
* 视频 业务类接口*/
public interface VideoService {


    /**
     * 查找全部
     * @return
     */
    List<Video> findAll();


    /**
     * 根据id查找
     * @param id
     * @return
     */
    Video findById(int id);


    Integer update(Video video);

    Integer save(Video video);//增加


    Integer delete(int id); // 删

}
