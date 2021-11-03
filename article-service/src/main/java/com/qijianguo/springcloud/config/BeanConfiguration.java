package com.qijianguo.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author qijianguo
 */
@Configuration
public class BeanConfiguration {


    /*@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }*/

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
