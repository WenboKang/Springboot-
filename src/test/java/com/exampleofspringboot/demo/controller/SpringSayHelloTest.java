package com.exampleofspringboot.demo.controller;


import com.exampleofspringboot.demo.DemoApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.temporal.Temporal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
/**
 *  第一个属性：装载Springboot的配置  （启动类）
 *  第二个属性： 端口
 */
@SpringBootTest(classes = DemoApplication.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringSayHelloTest {

    @LocalServerPort
    private  int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws MalformedURLException {
        this.base = new URL("http://localhost:"+port+"/");

    }

    @Test
    public void sayHi() {
        ResponseEntity<String> responseEntity = template.getForEntity(base.toString(), String.class);
        String entityBody = responseEntity.getBody();
        assertThat(entityBody , equalTo("Hello ShouQianBa"));

    }
}