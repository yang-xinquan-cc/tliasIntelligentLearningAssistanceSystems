package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class deptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> getDepts() {
        return deptMapper.list();
    }

    /**
     * 根据id删除部门的同时还需删除其部门下的员工,如果有错误需要事务的回滚
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) throws Exception {
        deptMapper.delectById(id);
        /*
        int i=1/0;
        if(true){
            throw new Exception("出错了....");
        }*/
        empMapper.deleteByDeptId(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {
        Dept dept=deptMapper.select(id);
        return dept;
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }

}
