package com.qisicheng.controller;

import com.qisicheng.pojo.user;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qinsicheng
 * @Description 内容：
 * @Date 31/01/2022 11:19
 */

@RestController
public class Controller {
    @GetMapping("hello")
    public String hello() {
        return "hello Spring-Security";
    }
    @GetMapping("index")
    public String login() {
        return "success";
    }
    @GetMapping("findAll")
    public String findAll() {
        return "hasAuthority";
    }
    @GetMapping("findAll_")
    public String findAll_() {
        return "hasAnyAuthority";
    }
    @GetMapping("unauth")
    public String unauth() {
        return "抱歉您没有权限访问";
    }

    @Secured({"ROLEs_normal"})
    @GetMapping("/anaTest")
    public String anaTest() {
        return "你好啊";
    }

    @GetMapping("/preAuthorize")
    @PreAuthorize("hasAnyAuthority('tom','alan')")
    public String preAuthorize(){
        return "preAuthorize"; }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('tom')")
    @PostFilter("filterObject.name == 'admin1'")
    public List<user> getAll(){
        ArrayList<user> list = new ArrayList<>();
        list.add(new user(1,"admin1","666"));
        list.add(new user(2,"admin2","888"));
        return list;
    }

    @GetMapping("getTestPreFilter")
    @PreAuthorize("hasAnyAuthority('tom','alan')")
    @PreFilter(value = "filterObject.id%1==0")
    @ResponseBody
    public List<user> getTestPreFilter(@RequestBody List<user>
                                                   list){
        list.forEach(t-> {
            System.out.println(t.getId()+"\t"+t.getName());
        });
        return list;
    }
}
