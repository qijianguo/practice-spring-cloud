package com.qijianguo.springcloud.config;


import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 自定义拦截器：用于权限校验
 * @author qijianguo
 */
public class AuthRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        //
    }
}
