package com.lzy.tmall.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String user= (String) request.getSession().getAttribute("user");
        if(user!=null){
            return true;
        }
        request.setAttribute("msg","没有权限请登录");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }
}
