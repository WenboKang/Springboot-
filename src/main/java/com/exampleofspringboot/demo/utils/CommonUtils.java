package com.exampleofspringboot.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**工具类
 *
 * @auther kangwenbo
 * @create 2020-04-25 18:39
 **/
public class CommonUtils {
    /**
     * 生成 UUID
     * @return
     */
    public static String generateUUID(){
        String uuid = UUID.randomUUID().toString()
                .replaceAll("-", "")    // 去掉横杠 -
                .substring(0,32);   //截取 32位

        return uuid;
    }


    /**
     * md5常用工具类, 加密
     * @param data
     * @return
     */
    public static String MD5(String data){
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }



}
