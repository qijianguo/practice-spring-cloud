package com.qijianguo.springcloud.eureka.client.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello" + name;
    }
}
