package com.exampleofspringboot.demo.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @auther kangwenbo
 * @create 2020-06-02 21:36
 **/
public class WXPayUtilTest {

    @Test
    public void xmlToMap() throws Exception {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<xml>\n" +
                "<sex>male</sex>\n" +
                "<name>Combo</name>\n" +
                "</xml>";
        Map<String, String> map = WXPayUtil.xmlToMap(s);
        System.out.println(map.get("name"));
        System.out.println(map.get("sex"));

    }

    @Test
    public void mapToXml() throws Exception {
        Map map = new HashMap<>();
        map.put("name","Combo");
        map.put("sex", "male");

        String s = WXPayUtil.mapToXml(map);
        System.out.println(s);
    }

    @Test
    public void getLogger() {
    }
}