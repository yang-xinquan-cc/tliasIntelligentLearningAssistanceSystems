package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class empServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;

    /*
    @Override
    public PageBean page(Integer page, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Long total=empMapper.count();
        Integer start=(page-1)*pageSize;
        List<Emp> row=empMapper.page(start,pageSize);
        pageBean.setTotal(total);
        pageBean.setRows(row);
        return pageBean;
    }
    */
    @Override
    public PageBean page(Integer page, Integer pageSize,
                         String name, Short gender,
                         LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> list = empMapper.list(name,gender,begin,end);
        Page<Emp> p=(Page<Emp>)list;
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getEmpById(Integer id) {
        return empMapper.getEmpById(id);
    }

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
