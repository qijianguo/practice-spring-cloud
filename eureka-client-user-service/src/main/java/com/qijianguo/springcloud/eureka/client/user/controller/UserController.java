package com.qijianguo.springcloud.eureka.client.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qijianguo
 */
@RestController
@Controller("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello linglingqi";
    }
}
