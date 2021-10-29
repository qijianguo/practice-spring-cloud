package com.qijianguo.springcloud.resttemplate.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author qijianguo
 */
@RibbonClient(name = "spring-rest-template", configuration = RibbonConfiguration.class)
public class RibbonClientConfig {
}
