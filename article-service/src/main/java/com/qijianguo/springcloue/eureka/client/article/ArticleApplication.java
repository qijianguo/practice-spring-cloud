package com.qijianguo.springcloue.eureka.client.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author qijianguo
 */
@SpringBootApplication(scanBasePackages = "com.qijianguo.springcloue.eureka.client.article")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.qijianguo.springcloud.eureka.client.user.interfaces")
public class ArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }
}
