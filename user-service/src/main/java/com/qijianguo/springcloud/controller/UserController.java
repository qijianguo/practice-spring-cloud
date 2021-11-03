package com.qijianguo.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @HystrixCommand(
            fallbackMethod = "helloFallback",
            // Hystrix参数配置方式
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            }
    )
    @GetMapping("/hello")
    public String hello(String name) {
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello" + name;
    }

//    @HystrixCommand(
//            fallbackMethod = "helloFallback",
//            // Hystrix参数配置方式
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
//            }
//    )
    @GetMapping("/hello2")
    public String hello2(String name) {
        try {
            Thread.sleep(1000 * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello" + name;
    }


    public String helloFallback(String name) {
        return "user-service fail";
    }
}
