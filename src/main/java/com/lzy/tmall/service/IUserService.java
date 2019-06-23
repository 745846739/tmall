package com.lzy.tmall.service;

import com.lzy.tmall.bean.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    User login(User user);

    Boolean register(User user);

    String checkRemembered(HttpServletRequest request);

    Boolean active(String username, String code);

    Boolean checkRegisterd(User user);
}
