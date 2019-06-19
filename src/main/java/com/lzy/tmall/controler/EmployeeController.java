package com.lzy.tmall.controler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzy.tmall.bean.Employ;
import com.lzy.tmall.mapper.EmployMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployMapper employMapper;
    //分页查询
    @GetMapping("/emps")
    public String emps(Model model, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Employ> pageInfo=new PageInfo<Employ>(employMapper.findAll());
        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "emp/list";
    }
    //去添加页面
    @GetMapping("/emp")
    public String toAdd(){
        return "emp/add";
    }
    //添加
    @PostMapping("/emp")
    public String add(Employ employ){
        employMapper.add(employ);
        return "redirect:/emps";
    }
    //去修改页面
    @GetMapping("/emp/{id}")
    public String toEdit(@PathVariable Integer id,Model model){
        Employ emp=employMapper.findById(id);
        model.addAttribute("emp",emp);
        return "emp/add";
    }
    //修改
    @PutMapping("/emp")
    public String edit(Employ employ){
        employMapper.update(employ);
        return "redirect:/emps";
    }
    //删除
    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable Integer id){
        employMapper.deleteById(id);
        return "redirect:/emps";
    }
}
