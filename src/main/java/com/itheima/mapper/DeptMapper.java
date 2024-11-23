package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
     * 查询全部部门数据
     * */
    @Select("select * from dept")
    List<Dept> list();
    /**
     * 根据id删除部门数据
     * @param id
     */
    @Delete("delete from dept where id=#{id}")
    void delectById(Integer id);

    /**
     * 插入部门数据
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    @Select("select * from dept where id=#{id}")
    Dept select(Integer id);

    /**
     * 根据id修改部门信息
     * @param dept
     * @return
     */
    @Update("update dept set name=#{name} where id=#{id}")
    void update(Dept dept);
}
