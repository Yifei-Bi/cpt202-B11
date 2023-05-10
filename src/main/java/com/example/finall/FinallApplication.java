package com.example.finall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//(exclude={DataSourceAutoConfiguration.class})

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
//@MapperScan("com.example.finall.dao")
public class FinallApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinallApplication.class, args);
    }

}
