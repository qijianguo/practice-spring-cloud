package com.qijianguo.springcloud.interfaces;

import org.springframework.stereotype.Component;

/**
 * @author qijianguo
 */
//@Component
public class UserRemoteClientFallback implements UserRemoteClient {
    @Override
    public String hello(String name) {
        return "user-hello-fail";
    }

    @Override
    public String hello2(String name) {
        return "user-hello2-fail";
    }
}
