package com.qijianguo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author qijianguo
 */
@SpringBootApplication
@EnableEurekaClient
public class ZuulFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulFileApplication.class, args);
    }

}
