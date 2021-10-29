package com.qijianguo.springcloud.resttemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ribbon API的使用
 * @author qijianguo
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 获取服务信息
     * {@url http://192.168.2.127:8085/ribbon/choose/spring-rest-template}
     * @return
     */
    @GetMapping("/choose/{serviceId}")
    public Object getInfo(@PathVariable String serviceId) {
        ServiceInstance choose = loadBalancerClient.choose(serviceId);
        return choose;
    }

}
