package com.lzy.tmall.mapper;

import com.lzy.tmall.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    public User login(User user);

    @Insert("insert user values(null,#{username},#{password})")
    public Integer save(User user);
}
