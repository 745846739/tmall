package com.lzy.tmall.service.impl;

import com.lzy.tmall.bean.User;
import com.lzy.tmall.mapper.UserMapper;
import com.lzy.tmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        User loginUser=userMapper.login(user);
        if(loginUser!=null && loginUser.getIsActive()==0){
            return null;
        }
        return loginUser;
    }

    @Override
    public Boolean register(User user) {
        Integer i=userMapper.save(user);
        String code = UUID.randomUUID().toString();
        userMapper.saveCode(user.getUsername(),code);
        return false;
    }

    @Override
    public String checkRemembered(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        for (Cookie ck : cookies) {
            if (ck.getName().equals("loginUser"))
                username = ck.getValue();
        }
        return username;
    }

    @Override
    public Boolean active(String username, String code) {
        Integer i=userMapper.checkCode(username,code);
        if (i!=null && i>0){
            userMapper.active(username);
            userMapper.deleteTemp(username);
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkRegisterd(User user) {
        Integer i=userMapper.checkRegisterd(user.getUsername());
        if (i>0){
            return true;
        }
        return false;
    }
}