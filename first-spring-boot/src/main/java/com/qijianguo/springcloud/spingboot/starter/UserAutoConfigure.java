package com.qijianguo.springcloud.spingboot.starter;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 第三步：自动构建客户端
 * @author qijianguo
 */
@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfigure {

    /**
     * ConditionalOnProperty 指定是否开启配置的功能
     * 只有当启动类添加了`@EnableUserClient` 并且 配置文件中 添加了 `spring.user.enabled = true`
     * 才会自动配置UserClient
     * @param userProperties
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "spring.user", value = "enabled", havingValue = "true")
    public UserClient userClient(UserProperties userProperties) {
        return new UserClient(userProperties);
    }

}
