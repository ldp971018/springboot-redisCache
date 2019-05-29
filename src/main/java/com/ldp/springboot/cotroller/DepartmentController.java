package com.ldp.springboot.cotroller;

import com.ldp.springboot.bean.Department;
import com.ldp.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.crypto.Des3;

/**
 * @author Return
 * @create 2019-05-28 16:21
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/dept/{id}")
    public Department getDepartById(@PathVariable("id") Integer id){
        return departmentService.getDeptById(id);
    }
}
