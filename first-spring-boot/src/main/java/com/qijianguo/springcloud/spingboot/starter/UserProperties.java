package com.qijianguo.springcloud.spingboot.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 第一步：创建Starter配置类
 * @author qijianguo
 */
@ConfigurationProperties("spring.user")
public class UserProperties {

    // 相当于：spring.user.name = XXX
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
