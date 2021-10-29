package com.qijianguo.springcloud.resttemplate.config;

import com.netflix.client.IClientConfigAware;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义负载均衡策略
 * @author qijianguo
 */
public class MyLoadBalancedRule implements IRule, IClientConfigAware {

    private ILoadBalancer loadBalancer;

    public MyLoadBalancedRule() {
    }

    @Override
    public Server choose(Object key) {
        // 定义策略
        List<Server> allServers = loadBalancer.getAllServers().stream().filter(server -> server.isAlive()).collect(Collectors.toList());
        // ...
        if (!CollectionUtils.isEmpty(allServers)) {
            return allServers.get(0);
        }
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.loadBalancer = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return loadBalancer;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
