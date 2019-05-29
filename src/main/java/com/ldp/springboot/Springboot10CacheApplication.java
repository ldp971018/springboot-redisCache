package com.ldp.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * springboot中cache的整合
 *  1）、配置好数据源和mybatis
 *  2）、开启自动缓存的功能
 */

@EnableCaching
@MapperScan(value = {"com.ldp.springboot.mapper"})
@SpringBootApplication
public class Springboot10CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot10CacheApplication.class, args);
    }

}
