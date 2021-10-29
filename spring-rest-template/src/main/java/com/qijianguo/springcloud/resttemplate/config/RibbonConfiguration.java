package com.qijianguo.springcloud.resttemplate.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon配置信息
 * @author qijianguo
 */
//@Configuration
public class RibbonConfiguration {

    //@Bean
    public IRule getRole() {
        // 自定义负载均衡策略
        return new MyLoadBalancedRule();
//        return new RetryRule();
    }

}
