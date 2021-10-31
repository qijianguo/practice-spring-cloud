package com.qijianguo.springcloue.eureka.client.article.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qijianguo.springcloud.eureka.client.user.interfaces.UserRemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRemoteClient userRemoteClient;

    @HystrixCommand(
            fallbackMethod = "helloFallback",
            // Hystrix参数配置方式
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            }
    )
    @GetMapping("/hello")
    public String hello() {
        // String forObject = restTemplate.getForObject("http://127.0.0.1:8081/hello", String.class);
        Map<String, Object> map = new HashMap<>(1);
        map.put("name", "凌凌漆");
//         String forObject = restTemplate.getForObject("http://user-service/user/hello", String.class, map);
        String forObject = userRemoteClient.hello("凌凌漆");
        return forObject + " welcome to reading...";
    }

    public String helloFallback() {
        return "fail";
    }

}
