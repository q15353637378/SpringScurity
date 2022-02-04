package com.qisicheng.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qisicheng.Mapper.userMapper;
import com.qisicheng.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qinsicheng
 * @Description 内容：
 * @Date 31/01/2022 12:05
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    userMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user = userMapper.selectOne(new QueryWrapper<user>().eq("name", username));
        if (user == null) {
            throw new UsernameNotFoundException("用戶名不存在");
        }
        //设置权限级集
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("tom,alan");
        return new User(user.getName(), new BCryptPasswordEncoder().encode(user.getPassword()), auth);
    }
}
