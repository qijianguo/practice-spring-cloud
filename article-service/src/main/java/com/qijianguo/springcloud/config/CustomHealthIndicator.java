package com.qijianguo.springcloud.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 自定义健康检查
 * 例如：服务依赖的中间件出现故障，则将故障信息传递到eureka-server
 * @author qijianguo
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.down().withDetail("status", false);
        // http://192.168.0.16:8082/actuator/health
        // {"status":"DOWN"}
    }
}
