package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 根据页码和每页的记录数返回封装的PageBean对象
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize,String name, Short gender,LocalDate begin,LocalDate end);

    /**
     * 批量删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 插入员工数据
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据id获取员工数据
     * @param id
     * @return
     */
    Emp getEmpById(Integer id);

    /**
     * 根据id来更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 根据用户名和密码查询员工数据是否存在
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
