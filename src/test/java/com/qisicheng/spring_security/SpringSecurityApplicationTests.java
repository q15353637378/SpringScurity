package com.qisicheng.spring_security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qisicheng.Mapper.userMapper;
import com.qisicheng.pojo.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired
    userMapper userMapper;


    @Test
    void contextLoads() {
        String name = "alan";
        user name1 = userMapper.selectOne(new QueryWrapper<user>().eq("name", name));
        System.out.println(name1);
    }

}
