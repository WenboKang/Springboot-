package com.exampleofspringboot.demo.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @auther kangwenbo
 * @create 2020-06-02 21:15
 **/
public class CommonUtilsTest {

    @Test
    public void generateUUID() {
        String uuid = CommonUtils.generateUUID();
        assertEquals(uuid.length(),32);

    }

    @Test
    public void MD5() {
        System.out.println(CommonUtils.MD5("我爱你"));
    }
}