package com.qijianguo.springcloud.starter;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 第二步：创建Starter客户端
 * @author qijianguo
 */
public class UserClient {

    @Autowired
    private UserProperties userProperties;

    public UserClient() {
    }

    public UserClient(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    public String getName() {
        return this.userProperties.getName();
    }
}
