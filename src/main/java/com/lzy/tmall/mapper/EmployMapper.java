package com.lzy.tmall.mapper;

import com.github.pagehelper.Page;
import com.lzy.tmall.bean.Employ;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmployMapper {
    @Select("select * from employee")
    Page<Employ> findAll();
    @Insert("insert into employee values(null,#{name},#{mail},#{birthday})")
    void add(Employ employ);
    @Delete("delete from employee where id=#{id}")
    void delete(Integer id);
    @Select("select * from employee where id=#{id}")
    Employ findById(Integer id);
    @Update("update employee set name=#{name},mail=#{mail},birthday=#{birthday} where id=#{id}")
    void update(Employ employ);
}
