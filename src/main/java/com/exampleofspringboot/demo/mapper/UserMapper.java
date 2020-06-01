package com.exampleofspringboot.demo.mapper;

import com.exampleofspringboot.demo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @auther kangwenbo
 * @create 2020-05-31 16:03
 **/
public interface UserMapper {

    /**
     * 根据主键id 查找
     * @param id
     * @return
     */
    @Select(("SELECT * FROM user WHERE id =  #{id}"))
    User  findById (@Param("id") int id);


    /**
     * 根据openid查找用户
     * @param openid
     * @return
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    User findByOpenId (@Param("openid") String openid);


    /**
     * 保存用户信息
     * @param user
     * @return
     */
    @Insert("INSERT INTO `user` ( `openid`, `name`, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time`)" +
            "VALUES" +
            "(#{openid},#{name},#{headImg},#{phone},#{sign},#{sex},#{city},#{createTime});")
    @Options(useGeneratedKeys = true , keyProperty = "id" , keyColumn = "id")
    int save(User user);


}
