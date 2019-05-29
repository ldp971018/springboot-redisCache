package com.ldp.springboot;

import com.ldp.springboot.bean.Employee;
import com.ldp.springboot.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.SpringTemplateLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot10CacheApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;    //简化字符串操作的k-v

    @Autowired
    RedisTemplate redisTemplate;//操作对象的k-v

    @Autowired
    RedisTemplate empRedisTemplate;  //序列化了的redis

    //redis中常见的五大数据类型
    //String(字符串)、List(列表)、set（集合）、hash（散列）、zset（有序集合）


    @Test
    public void test01(){
        //1.向redis中存放一个字符串
        //stringRedisTemplate.opsForValue().append("msg","hello");
        //2.获取redis内的信息
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println("msg===>"+msg);
        //3.向redis中添加集合
//        stringRedisTemplate.opsForList().leftPush("list1","1");
//        stringRedisTemplate.opsForList().leftPush("list1","2");
//        stringRedisTemplate.opsForList().leftPush("list1","3");
//        stringRedisTemplate.opsForList().leftPush("list1","4");
//        stringRedisTemplate.opsForList().leftPush("list1","5");
        //4.获取redis中的集合信息
//        String list1 = stringRedisTemplate.opsForList().index("list1", 1);
//        String list1 = stringRedisTemplate.opsForList().leftPop("list1");
        String list1 = stringRedisTemplate.opsForList().rightPop("list1");
        System.out.println("list中索引1==>"+list1);
            
    }

    //测试保存一个对象的操作
    @Test
    public void test02(){
        Employee employee = employeeMapper.getEmpById(1);
        //1、如果使用默认 配置，使用了默认的jdk序列化机制，序列化的数据保存到 redis中
        //redisTemplate.opsForValue().set("emp-01",employee);
        //2、如果将数据以json的方式保存
        //1）、自己将对象转为json
        //2）、redisTemplate默认的序列化规则
        //empRedisTemplate.opsForValue().set("emp-01",employee);
        Employee employee1 = (Employee) empRedisTemplate.opsForValue().get("emp-01");
        System.out.println(employee);
    }


    @Test
    public void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

}
