package com.qijianguo.springcloud.config;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Eureka状态变化监听
 * 服务的上下线等
 * @author qijianguo
 */
@Component
public class EurekaStateChangeListener {

    private static final Logger logger = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info(instanceInfo.getAppName() + " 注册了");
    }

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        logger.info(event.getServerId() + " 下线了");
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        logger.info(event.getServerId() + " 重新续约");
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info("注册中心启动...");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info("Eureka server start...");
    }
}
