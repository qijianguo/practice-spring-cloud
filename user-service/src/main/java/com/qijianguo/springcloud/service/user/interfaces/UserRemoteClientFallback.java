package com.qijianguo.springcloud.service.user.interfaces;

import org.springframework.stereotype.Component;

/**
 * @author qijianguo
 */
@Component
public class UserRemoteClientFallback implements UserRemoteClient {
    @Override
    public String hello(String name) {
        return "user-hello-fail";
    }
}
