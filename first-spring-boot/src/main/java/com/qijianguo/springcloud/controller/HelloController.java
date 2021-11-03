package com.qijianguo.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qijianguo
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(String name) {
        return "Hello!" + name;
    }
}
