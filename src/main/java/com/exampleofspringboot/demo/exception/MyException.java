package com.exampleofspringboot.demo.exception;

/**
 * 自定义的异常
 * @auther kangwenbo
 * @create 2020-06-06 14:22
 **/
public class MyException extends  RuntimeException {
    /*自定义属性*/
    /**
     * 状态码
     */
    private Integer code ;
    /**
     * 异常消息
     */
    private String msg ;

    public MyException(){
        super();
    }

    public MyException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
