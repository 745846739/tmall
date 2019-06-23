package com.lzy.tmall.config;

import com.lzy.tmall.component.MyLocaleResolver;
import com.lzy.tmall.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import java.util.Date;


@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //请求路径映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/home").setViewName("home");
    }
    //注入国际化解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
    //注入登录拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns("/","/register","/login","/active","/asserts/**");
//    }
    //
    @Bean
    public Formatter<Date> dateFormatter(){
        return new DateFormatter("yyyy-mm-dd");
    }
}
