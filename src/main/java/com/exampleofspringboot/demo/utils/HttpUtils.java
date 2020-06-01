package com.exampleofspringboot.demo.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装HTTP的 get post 等封装方法
 * @auther kangwenbo
 * @create 2020-05-28 20:50
 **/
public class HttpUtils {

    private static final Gson gson = new Gson();

    /**
     * get 方法
     * @param url
     * @return
     */
    public static Map<String , Object> doGet(String url){
        Map<String, Object> map = new HashMap<>();

        CloseableHttpClient httpClient = HttpClients.createDefault(); //客户端
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) //连接超时
                .setConnectionRequestTimeout(5000)  //  请求超时
                .setRedirectsEnabled(true)  //允许自动重定向
                .build();

        //get请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);   //设置配置

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet); //响应
            if (httpResponse.getStatusLine().getStatusCode() == 200){
                //请求状态码为200
                HttpEntity entity = httpResponse.getEntity();
                String jsonResult = EntityUtils.toString(entity);   //返回的实体（String形式）
                map = gson.fromJson(jsonResult , map.getClass());

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭客户端
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return map;
    }


    /**
     * 封装post
     * @param url
     * @param data
     * @return
     */
    public static String doPost(String url , String data , int timeout){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setRedirectsEnabled(true)
                .build();

        HttpPost httpPost = new HttpPost();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type","text/html;chartset=UTF-8");

        if (data!=null && data instanceof String){  //使用字符串传参
            StringEntity stringEntity = new StringEntity(data, "UTF-8");
            httpPost.setEntity(stringEntity);
        }

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() == 200){
                //请求状态码为200

                String jsonResult = EntityUtils.toString(httpEntity);   //返回的实体（String形式）
                return jsonResult ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭客户端
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
