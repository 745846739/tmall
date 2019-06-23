package com.lzy.tmall.mapper;

import com.lzy.tmall.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    public User login(User user);

    @Insert("insert into user values(#{username},#{password},#{mail},#{isActive})")
    public Integer save(User user);

    @Insert("insert into temp_active values(#{username},#{code})")
    void saveCode(String username, String code);

    @Select("select * from temp_active where username=#{username} and code=#{code}")
    Integer checkCode(String username, String code);

    @Update("update user set isActive=1 where username=#{username}")
    void active(String username);

    @Delete("delete from temp_active where username=#{username}")
    void deleteTemp(String username);

    @Select("select count(*) from user where username=#{username}")
    Integer checkRegisterd(String username);

    @Select("select password from user where username=#{username}")
    String getPasswordByUsername(String username);
}
