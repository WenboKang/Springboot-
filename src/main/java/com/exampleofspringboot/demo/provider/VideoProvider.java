package com.exampleofspringboot.demo.provider;

import com.exampleofspringboot.demo.domain.Video;
import org.apache.ibatis.jdbc.SQL;

/**
 * @auther kangwenbo    video 构建动态SQL语句
 * @create 2020-05-12 17:32
 **/
public class VideoProvider {

    /**
     * 更新video动态语句
     * @param video
     * @return
     */
    public String updateVideo(final Video video){
        return new SQL(){{
            UPDATE("video");
            //条件写法
            if (video.getTitle()!=null){
                SET("title=#{title}");
            }
            if (video.getSummary()!=null){
                SET("summary=#{summary}");
            }
            if (video.getCoverImg()!=null){
                SET("coverImg=#{coverImg}");
            }
            if (video.getViewNum()!=null){
                SET("viewNum=#{viewNum}");
            }
            if (video.getPrice()!=null){
                SET("price=#{price}");
            }
            if (video.getCreateTime()!=null){
                SET("createTime=#{createTime}");
            }
            if (video.getOnline()!=null){
                SET("online=#{online}");
            }
            if (video.getPoint()!=null){
                SET("point=#{point}");
            }

            WHERE("id=#{id}");

        }}.toString();

    }

}
