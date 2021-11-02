package com.qijianguo.springcloud.service.user.interfaces;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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
                return " fail";
            }
        };
    }
}
