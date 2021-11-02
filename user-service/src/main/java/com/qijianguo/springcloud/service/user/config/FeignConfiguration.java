package com.qijianguo.springcloud.service.user.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Feign配置
 * @author qijianguo
 */
@Configuration
public class FeignConfiguration {

    /**
     * Feign日志配置
     * NONE: 不输出日志
     * BASIC:只输出请求方的URL和响应状态码以及接口执行的时间
     * HEADERS:将BASIC信息和请求头信息输出
     * FULL:输出完整的请求信息
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 超时时间设置
     * @return
     */
    @Bean
    public Request.Options options() {
        /**
         * connectTimeoutMillis:
         * readTimeoutMillis:
         */
        return new Request.Options(2000, 10000);
    }

    /**
     * Basic认证
     * @return
     */
    /*@Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("", "");
    }*/



}
