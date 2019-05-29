package com.ldp.springboot.mapper;

import com.ldp.springboot.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @author Return
 * @create 2019-05-28 9:48
 */
@Mapper
public interface EmployeeMapper {

    //根据id查询员工信息
    @Select("select * from employee where id=#{id}")
    public Employee getEmpById(Integer id);

    //根据员工id插入信息
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public int insertEmp(Employee employee);

    //删除员工信息id
    @Delete("delete from employee where id=#{id}")
    public int deleteEmp(Integer id);

    //员工更新方法
    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId}")
    public int updateEmp(Employee employee);

    @Select("select * from employee where lastName=#{lastName}")
    Employee getEmpByName(String lastName);
}
