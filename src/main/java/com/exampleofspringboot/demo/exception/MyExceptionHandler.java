package com.exampleofspringboot.demo.exception;

import com.exampleofspringboot.demo.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理控制器
 * @auther kangwenbo
 * @create 2020-06-06 14:27
 **/
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e){
        if (e instanceof MyException ){ // 如果是自定义的异常
            MyException myException = (MyException) e;  //转成自定义的异常
            return JsonData.buildError(myException.getCode() , myException.getMsg());
        }else{
            return JsonData.buildError("全局异常 ， 未知错误");
        }
    }

}
