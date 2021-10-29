package com.qijianguo.springcloud.eureka.client.user.interfaces;

import com.qijianguo.springcloud.eureka.client.user.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author qijianguo
 */
@Component
@FeignClient(name = "user-service", configuration = FeignConfiguration.class)
public interface UserRemoteClient {

    @GetMapping("/user/hello")
    String hello(@RequestParam("name") String name);
}

