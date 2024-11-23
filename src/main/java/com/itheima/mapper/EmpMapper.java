package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 获取emp记录数
     * @return
     */
    /*
    @Select("select count(*) from emp")
    Long count();
    */
    /**
     * 根据
     * @return
     */
    /*
    @Select("select * from emp limit #{start},#{limit}")
    List<Emp> page(Integer start, Integer limit);
    */
    //@Select("select * from emp")
    List<Emp> list(String name, Short gender,LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            " values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp getEmpById(Integer id);


    void update(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);

    @Delete("delete from emp where dept_id=#{id}")
    void deleteByDeptId(Integer id);
}
