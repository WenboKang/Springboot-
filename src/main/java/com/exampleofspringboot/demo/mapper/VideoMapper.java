package com.exampleofspringboot.demo.mapper;

import com.exampleofspringboot.demo.domain.Video;

import com.exampleofspringboot.demo.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther kangwenbo
 * @create 2020-05-07 15:42
 **/
/*
* video 数据访问层
* */
public interface VideoMapper {

    @Select("select * from video")
    List<Video>  findAll();

    /**
     * 根据 videoId查找
     * @param id
     * @return
     */
    @Select("select * from video where id= #{id}")
    Video findById(int id);

    //@Update("UPDATE video SET title=#{title} WHERE id=#{id} ")
    @UpdateProvider(type = VideoProvider.class , method = "updateVideo")
    Integer update(Video video);// 更新

    @Insert("INSERT INTO `video` (`title`,`summary`,`cover_img`,`view_num`,`price`,`create_time`,`online`,`point`)" +
            "VALUES (#{title},#{summary},#{coverImg},#{viewNum},#{price},#{createTime},#{online},#{point});")
    @Options(useGeneratedKeys = true , keyProperty = "id",keyColumn = "id")//保存自增ID映射到对象中
    Integer save(Video video); //增加

    @Delete("DELETE FROM video WHERE id = #{id}")
    Integer delete(int id); // 删
}
