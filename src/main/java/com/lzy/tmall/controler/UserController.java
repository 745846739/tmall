package com.lzy.tmall.controler;

import com.lzy.tmall.bean.User;
import com.lzy.tmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model){
        User user1=userService.login(user);
        if(user1!=null){
            session.setAttribute("user",user1.getUsername());
            return "home";
        }
        model.addAttribute("msg","用户名或密码错误");
        return "login";
    }
    @PostMapping("/register")
    public String register(User user,Model model){
        Boolean b=userService.register(user);
        if(b){
            model.addAttribute("msg","注册成功");
            return "login";
        }
        model.addAttribute("msg","注册失败");
        return "register";
    }
    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }
}