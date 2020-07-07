package com.exampleofspringboot.demo.services;

import com.exampleofspringboot.demo.domain.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @auther kangwenbo
 * @create 2020-06-01 17:19
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;



    @Test
    public void findAll() {
        List<Video> all = videoService.findAll();
        assertNotNull(all);

    }

    @Test
    public void findById() {
        Video video = videoService.findById(1);
        assertNotNull(video);
        String title = video.getTitle();
        assertEquals(title , "高级教程SpringBoot");
    }

    @Test
    public void update() {
        Video video = new Video();
        video.setId(3);
        video.setTitle("新title");
        video.setCoverImg("新缩略图");
        video.setOnline(666);
        video.setPoint(10.0);
        videoService.update(video);
    }

    @Test
    @Transactional
    public void save() {
        Video video = new Video();
        video.setTitle("新title");
        video.setCoverImg("新缩略图");
        video.setOnline(10);
        video.setPoint(10.0);
        videoService.save(video);
    }

    @Test
    public void delete() {
    }
}