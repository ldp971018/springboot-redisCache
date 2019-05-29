package com.ldp.springboot.service;

import com.ldp.springboot.bean.Department;
import com.ldp.springboot.mapper.DepartmentMapper;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Return
 * @create 2019-05-28 16:18
 */
@CacheConfig(cacheNames = {"dept"},cacheManager = "deptCacheManager")
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Cacheable(key = "#id")
    public Department getDeptById(Integer id){
        return departmentMapper.getDeptById(id);
    }


}
