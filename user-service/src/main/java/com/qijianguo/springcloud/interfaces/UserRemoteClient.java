package com.qijianguo.springcloud.interfaces;

import com.qijianguo.springcloud.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author qijianguo
 */
@Component
//@FeignClient(name = "user-service", configuration = FeignConfiguration.class, fallback = UserRemoteClientFallback.class)
@FeignClient(name = "user-service", configuration = FeignConfiguration.class, fallbackFactory = UserRemoteClientFallbackFactory.class)
public interface UserRemoteClient {

    @GetMapping("/user/hello")
    String hello(@RequestParam("name") String name);


    @GetMapping("/user/hello2")
    String hello2(@RequestParam("name") String name);


}