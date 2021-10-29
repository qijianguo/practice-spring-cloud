package com.qijianguo.springcloud.resttemplate.config;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;

public class MyLoadBalancerInterceptor implements ClientHttpRequestInterceptor {

    private LoadBalancerClient loadBalancerClient;
    private LoadBalancerRequestFactory loadBalancerRequestFactory;

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient, LoadBalancerRequestFactory loadBalancerRequestFactory) {
        this.loadBalancerClient = loadBalancerClient;
        this.loadBalancerRequestFactory = loadBalancerRequestFactory;
    }

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient) {
        this(loadBalancerClient, new LoadBalancerRequestFactory(loadBalancerClient));
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        final URI originUri = request.getURI();
        String host = originUri.getHost();
        System.out.println("进入自定义的请求拦截器中 " + host);
        Assert.state(host != null, "Request URI does not contain a valid hostname: " + originUri);
        return loadBalancerClient.execute(host, loadBalancerRequestFactory.createRequest(request, body, execution));
    }


}
