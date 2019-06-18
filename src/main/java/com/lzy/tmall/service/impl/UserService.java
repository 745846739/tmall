package com.lzy.tmall.service.impl;

import com.lzy.tmall.bean.User;
import com.lzy.tmall.mapper.UserMapper;
import com.lzy.tmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public Boolean register(User user) {
        Integer i=userMapper.save(user);
        if(i>0){
            return true;
        }
        return false;
    }
}
