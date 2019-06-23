package com.lzy.tmall.controler;

import com.lzy.tmall.bean.User;
import com.lzy.tmall.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    IUserService userService;
    //去登录页面
    @GetMapping("/login")
    public String toLogin(HttpServletRequest request,HttpSession session){
        //remembered则返回username，否则返回null
        String username=userService.checkRemembered(request);
        //remembered则在session中加入用户名并前往主页
        if(username!=null){
            session.setAttribute("loginUser",username);
            return "home";
        }
        //否则为首次登录，前往登录页面
        return "login";
    }
    //登录
    @PostMapping("/login")
    public String login(User user, String remember, HttpSession session, Model model, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            return "redirect:/home";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

//        User loginUser=userService.login(user);
//        if(loginUser!=null){
//            session.setAttribute("loginUser",loginUser.getUsername());
//            if(remember!=null){
//                Cookie loginUserCookie = new Cookie("loginUser", loginUser.getUsername());
//                loginUserCookie.setMaxAge(14*24*3600);
//                response.addCookie(loginUserCookie);
//            }
//            return "redirect:/home";
//        }
//        model.addAttribute("msg","用户名或密码错误或未激活");
//        return "login";
    }
    //去注册页面
    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }
    //注册
    @PostMapping("/register")
    public String register(User user,Model model){
        //查看是否已经注册
        Boolean registerd=userService.checkRegisterd(user);
        if (registerd){
            model.addAttribute("msg","您已注册过账号");
            return "login";
        }
        Boolean registerFinished=userService.register(user);
        if(registerFinished){
            return "toActive";
        }
        model.addAttribute("msg","注册失败");
        return "register";
    }
    //激活
    @GetMapping("/active")
    public String active(Model model, @RequestParam("username") String username,@RequestParam("code") String code){
        Boolean b=userService.active(username,code);
        if(b){
            model.addAttribute("msg","已激活，请登录");
            return "login";
        }
        return "toActive";
    }
    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        session.removeAttribute("loginUser");
        Cookie[] cookies = request.getCookies();
        for (Cookie ck:cookies) {
            if (ck.getName().equals("loginUser")){
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
        }
        return "login";
    }
}
