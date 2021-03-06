package com.qijianguo.springcloud;

import com.qijianguo.springcloud.randomport.StartCommand;
import com.qijianguo.springcloud.starter.EnableUserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qijianguo
 */
@SpringBootApplication
@EnableUserClient // 开启自定义Starter注解扫描
public class FirstSpringBootApplication {

    public static void main(String[] args) {
        // 设置启动参数，例如：随机端口
        new StartCommand(args);

        SpringApplication.run(FirstSpringBootApplication.class);
    }
}
