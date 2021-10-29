package com.qijianguo.springcloud.resttemplate.controller;

import com.qijianguo.springcloud.resttemplate.bean.HouseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/house/call")
public class HouseClientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        return restTemplate.getForObject("http://spring-rest-template/house/data?name=" + name, HouseInfo.class);
    }

    @GetMapping("/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        return restTemplate.getForObject("http://spring-rest-template/house/data/{name}", String.class, name);
    }

}
