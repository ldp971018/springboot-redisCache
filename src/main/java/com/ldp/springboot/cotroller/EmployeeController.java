package com.ldp.springboot.cotroller;

import com.ldp.springboot.bean.Employee;
import com.ldp.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Return
 * @create 2019-05-28 10:08
 */

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeService.getEmpById(id);
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee employee){
        employeeService.UpdateEmp(employee);
        return employee;
    }

    @GetMapping("/delEmp/{id}")
    public String delEmp(@PathVariable("id") Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }


}
