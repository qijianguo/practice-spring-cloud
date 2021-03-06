package com.qijianguo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author qijianguo
 */
@SpringBootApplication(scanBasePackages = "com.qijianguo.springcloud")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.qijianguo.springcloud.interfaces")
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }
}
