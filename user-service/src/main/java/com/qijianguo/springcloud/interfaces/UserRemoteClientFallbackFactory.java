package com.qijianguo.springcloud.interfaces;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author qijianguo
 */
@Component
public class UserRemoteClientFallbackFactory implements FallbackFactory<UserRemoteClient> {

    private final Logger LOGGER = LoggerFactory.getLogger(UserRemoteClientFallbackFactory.class);

    @Override
    public UserRemoteClient create(Throwable cause) {
        LOGGER.error(cause.getMessage(), cause.fillInStackTrace());
        return new UserRemoteClient() {
            @Override
            public String hello(String name) {
                return "hello fail";
            }
            @Override
            public String hello2(String name) {
                return "hello2 fail";
            }
        };
    }
}
