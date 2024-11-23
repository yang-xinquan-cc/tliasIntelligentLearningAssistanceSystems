package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门表的数据
     * @return
     */
    public List<Dept> getDepts();

    /**
     * 根据id删除部门数据
     * @param id
     */
    void deleteById(Integer id) throws Exception;

    /**
     * 添加部门
     */
    void add(Dept dept);

    /**
     * 根据id查询部门数据
     * @return
     */
    Dept getDeptById(Integer id);

    /**
     * 根据id修改部门名称
     * @param dept
     */
    void update(Dept dept);
}
