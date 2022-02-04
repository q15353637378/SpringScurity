package com.qisicheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author qinsicheng
 * @Description 内容：
 * @Date 31/01/2022 11:21
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    int i = 1;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        String encode = passwordEncoder.encode("123");
        auth.inMemoryAuthentication().withUser("luck").password(encode).roles("admin");
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
