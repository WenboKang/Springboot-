package com.exampleofspringboot.demo.interceoter;

import com.exampleofspringboot.demo.domain.JsonData;
import com.exampleofspringboot.demo.utils.JwtUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/** 拦截器
 * @auther kangwenbo
 * @create 2020-06-01 10:57
 **/
public class LoginInterceptor implements HandlerInterceptor {

    private static  final   Gson gson = new Gson();

    /**
     * 进入controller之前进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response,Object handler){
        String token = request.getHeader("token");
        if (token == null){
            token = request.getParameter("token");
        }
        if (token!=null){
            Claims claims = JwtUtils.checkJWT("token");
            Integer userId = (Integer)claims.get("id");
            String name = (String) claims.get("name");

            request.setAttribute("userid" , userId);
            request.setAttribute("name" , name);
            return true ;
        }
        sendJsonMessage(response , JsonData.buildError("请登录"));   // token==null
        return false ;
    }

    /**
     * 相应数据给前端
     * @param response
     * @param obj
     */
    public static  void sendJsonMessage(HttpServletResponse response , Object obj)  {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(gson.toJson(obj));
            writer.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
