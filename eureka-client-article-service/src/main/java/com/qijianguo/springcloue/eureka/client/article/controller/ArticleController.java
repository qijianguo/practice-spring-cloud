package com.qijianguo.springcloue.eureka.client.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author qijianguo
 */
@RestController
@Controller("/article")
public class ArticleController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        // String forObject = restTemplate.getForObject("http://127.0.0.1:8081/hello", String.class);
        String forObject = restTemplate.getForObject("http://eureka-client-user-service/hello", String.class);
        return forObject + " welcome to reading...";
    }

}
