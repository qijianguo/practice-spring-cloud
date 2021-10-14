package com.qijianguo.springcloud.eureka.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qijianguo
 */
@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
