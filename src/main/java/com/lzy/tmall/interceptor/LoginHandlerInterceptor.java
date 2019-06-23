package com.lzy.tmall.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginUser= (String) request.getSession().getAttribute("loginUser");
        //session中有用户名，放行
        if(loginUser!=null){
            return true;
        }
        //session中没有用户名，coockie中有，给session中添加，放行
        Cookie[] cookies = request.getCookies();
        String username = null;
        for (Cookie ck : cookies) {
            if (ck.getName().equals("loginUser"))
                username = ck.getValue();
        }
        if(username!=null){
            request.getSession().setAttribute("loginUser",username);
            return true;
        }
        //啥都没有
        request.setAttribute("msg","没有权限请登录");
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }
}
