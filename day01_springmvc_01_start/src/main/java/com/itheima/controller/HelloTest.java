package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloTest {
    @RequestMapping(path ="/success" )
    public String hello(){
        System.out.println("访问成功");
        return "hello";
    }
}
