package com.ldp.springboot.mapper;

import com.ldp.springboot.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Return
 * @create 2019-05-28 16:16
 */
@Mapper
public interface DepartmentMapper {

    //根据id查询的部门信息
    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);
}
