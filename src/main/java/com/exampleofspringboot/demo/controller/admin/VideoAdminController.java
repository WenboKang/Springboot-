package com.exampleofspringboot.demo.controller.admin;

import com.exampleofspringboot.demo.domain.Video;
import com.exampleofspringboot.demo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther kangwenbo   管理员
 * @create 2020-05-12 17:11
 **/
@RestController
@RequestMapping("/admin/api/v1/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    /**
     * 更新
     * @param video
     * @return
     */
    @PutMapping("/update")
    public Integer updateById(@RequestBody Video video ){
        Integer update = videoService.update(video);
        return update;
    }

    /**
     * 保存
     * @param video
     * @return
     */
    @PostMapping("/saveVideo")
    public Integer save(@RequestBody Video video ){
        return videoService.save(video);
    }

    /**
     * 根据id删除
     * @param videoId
     * @return
     */
    @DeleteMapping("/delById")
    public Object delById(@RequestParam(value = "video_id",required = true)int videoId){
        return videoService.delete(videoId);
    }


}
