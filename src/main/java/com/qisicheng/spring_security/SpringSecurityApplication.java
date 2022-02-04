package com.qisicheng.spring_security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//扫描容器内组件（要不然可能无法自动装配某些类）
@ComponentScan("com.qisicheng")
@SpringBootApplication
//Mapper扫描
@MapperScan("com.qisicheng")
//开启注解功能
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true)
public class SpringSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
}
