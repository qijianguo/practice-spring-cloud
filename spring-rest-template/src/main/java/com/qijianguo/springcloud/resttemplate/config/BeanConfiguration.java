package com.qijianguo.springcloud.resttemplate.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author qijianguo
 */
@Configuration
public class BeanConfiguration {

    @Bean
    @LoadBalanced
    // @MyLoadBalanced // 自定义负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
