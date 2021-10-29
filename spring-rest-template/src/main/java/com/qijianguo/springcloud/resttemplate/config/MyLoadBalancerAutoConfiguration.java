package com.qijianguo.springcloud.resttemplate.config;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 定义配置类，给 RestTemplate 注入自定义拦截器 {@link MyLoadBalancerInterceptor}
 * @author qijianguo
 */
@Configuration
public class MyLoadBalancerAutoConfiguration {

    @MyLoadBalanced
    @Autowired(required = false)
    private List<RestTemplate> restTemplates = Collections.emptyList();

    @Bean
    public MyLoadBalancerInterceptor getMyLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient) {
        return new MyLoadBalancerInterceptor(loadBalancerClient);
    }

    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer() {
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate restTemplate : restTemplates) {
                    List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
                    SpringClientFactory clientFactory = new SpringClientFactory();
                    LoadBalancerClient loadBalancerClient = new RibbonLoadBalancerClient(clientFactory);
                    list.add(getMyLoadBalancerInterceptor(loadBalancerClient));
                    restTemplate.setInterceptors(list);
                }

            }
        };
    }

}
