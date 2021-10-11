package com.qijianguo.springcloud.spingboot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试自定义Starter
 * @author qijianguo
 */
@RestController
public class UserController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/user")
    public Object getUser() {
        return userClient.getName();
    }
}
