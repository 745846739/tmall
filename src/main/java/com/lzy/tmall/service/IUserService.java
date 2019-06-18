package com.lzy.tmall.service;

import com.lzy.tmall.bean.User;
import org.springframework.stereotype.Service;

public interface IUserService {
    User login(User user);

    Boolean register(User user);
}
