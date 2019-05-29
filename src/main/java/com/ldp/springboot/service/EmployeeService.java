package com.ldp.springboot.service;

import com.ldp.springboot.bean.Employee;
import com.ldp.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author Return
 * @create 2019-05-28 10:07
 */
//CacheConfig利用这个注解，可以指定在该类中所有的缓存信息的名称
@CacheConfig(cacheNames = {"emp"})
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 根据id查询员工信息
     * @Cacheable开启的注解
     *  cacheNames:需要的指定名称
     *  condition:限定缓存的条件
     * @param id
     * @return
     */

    //Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
    @Cacheable(cacheNames = {"emp"},key ="#id",condition = "#id>0")
    public Employee getEmpById(Integer id){
        System.out.println("正在查询"+id+"号员工信息。");
        return employeeMapper.getEmpById(id);
    }


    /**
     * @CachePut 保证方法被调用，又希望结果被缓存
     * 修改了数据库的信息，又更新了缓存
     * 运行时机：
     *  先调用目标方法,在讲查询出来的result返回放入了缓存当中，但是放入的key值默认是的参数及employee
     *  所以如果需要得到第一查询的的结果，那么必须自定相同的key，
     *    key = "#employee.id"
     *    key="#result.id" 注意：但是cacheable不能的使用的result的指定id，那是因为cacheable的是调用目标方法之前执行的所以不可以
     *
     */
    @CachePut(cacheNames = {"emp"},key = "#employee.id")
    public int UpdateEmp(Employee employee){
        System.out.println("正在更新员工信息"+employee);
        return employeeMapper.updateEmp(employee);
    }

    /**
     * 根据id删除员工信息。
     * @CacheEvict：表示清空缓存信息。
     *  key:指定要清除的缓存的value值。
     *  allEntries：表示清除所有的缓存 信息。
     *  beforeInvocation =false ：表示的在方法执行之后进行缓存清除，如果方法执行的出现的异常，那么无法清除缓存。
     *  beforeInvocation =true ：表示的在方法执行之前进行缓存清除，无论方法是否执行成功，都将清除缓存。
     * @param id
     * @return
     */
    @CacheEvict(value = {"emp"},key="#id")
    public int deleteEmp(Integer id){
        System.out.println("正在删除的"+id+"员工信息");
       // employeeMapper.deleteEmp(id);
        return 1;
    }

    /**
     * 根据lastName查询的员工信息
     * Caching：可以设置多个缓存策略
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                   @Cacheable(key = "#lastName")
            },
            put = {
                    @CachePut(key="#result.id"),
                    @CachePut(key="#result.email")
            }
    )
    public Employee getEmpByName(String lastName){
        return employeeMapper.getEmpByName(lastName);
    }


}
