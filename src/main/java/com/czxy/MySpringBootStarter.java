package com.czxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 
 */
@SpringBootApplication
@MapperScan(basePackages = "com.czxy.*.dao")
public class MySpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootStarter.class,args);
    }

}
