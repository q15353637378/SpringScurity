package com.qisicheng.config;

import com.qisicheng.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author qinsicheng
 * @Description 内容：
 * @Date 31/01/2022 11:21
 */

@Configuration
public class SecurityConfig_Test extends WebSecurityConfigurerAdapter {
    //第三种自定义方法

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //自定义登录界面
                .loginPage("/login.html")   //登录页面绑定
                .loginProcessingUrl("/login")  //登录访问路径
                .defaultSuccessUrl("/index").permitAll()  //登录成功跳转地址
                .and()
                //哪些路径可以直接访问不需要认证
                .authorizeRequests()
                .antMatchers("/","/hello").permitAll() //指定 URL 无需保护
                //需要带特定权限的身份才可以登录
                .antMatchers("/findAll").hasAuthority("admin")
                //主体带有权限
                .antMatchers("/findAll_").hasAnyAuthority("tom")
                //其他请求需要认证
                .anyRequest() // 其他请求
                .authenticated() // 需要认证
                //关闭csrf防止
                .and().csrf().disable();
        http.exceptionHandling().accessDeniedPage("/unauth");
    }
}
