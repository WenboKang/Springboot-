package com.exampleofspringboot.demo.services.impl;

import com.exampleofspringboot.demo.domain.Video;
import com.exampleofspringboot.demo.mapper.VideoMapper;
import com.exampleofspringboot.demo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther kangwenbo
 * @create 2020-05-11 20:08
 **/
@Service
public class VideoServiceImpl  implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoMapper.findById(id);
    }

    @Override
    public Integer update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public Integer save(Video video) {
        Integer rows = videoMapper.save(video);
        System.out.println("保存对象的id为： "+ video.getId());
        return rows;
    }

    @Override
    public Integer delete(int id) {
        return videoMapper.delete(id);
    }
}
