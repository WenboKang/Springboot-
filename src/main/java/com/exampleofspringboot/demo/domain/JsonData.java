package com.exampleofspringboot.demo.domain;

import java.io.Serializable;

/**
 * @auther kangwenbo
 * @create 2020-05-28 15:25
 **/
public class JsonData implements Serializable {
    private Integer code ; //状态码 0：成功  1： 处理中   -1：失败
    private Object data ; //数据
    private String msg ; //描述

    public JsonData() {
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    /**
     * 成功传入数据
     * @return
     */
    public static JsonData buildSuccess(){
        return new JsonData(0,null,null);
    }
    /**
     * 成功传入数据
     * @return
     */
    public static JsonData buildSuccess(Object data){
        return new JsonData(0,data,null);
    }


    /**
     * 成功传入数据及描述信息
     * @param data
     * @param msg
     * @return
     */
    public static JsonData buildSuccess(Object data ,String msg){
        return new JsonData(0,data,msg);
    }

    /**
     * 成功传入 状态码 及 描述信息
     * @param code
     * @param msg
     * @return
     */
    public static JsonData buildSuccess(Integer code , String msg){
        return new JsonData(code,null,msg);
    }
    /**
     * 成功传入 状态码 及 数据
     * @param code
     * @param data
     * @return
     */
    public static JsonData buildSuccess(Integer code , Object data){
        return new JsonData(code,data,null);
    }

    /**
     * 传入失败 ， 传入描述信息
     * @param msg
     * @return
     */
    public static JsonData buildError(String msg){
        return new JsonData(-1 , null,msg);
    }

    public static JsonData buildError(Integer code,String msg){
        return new JsonData(code , null,msg);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData[" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg  +
                ']';
    }
}
