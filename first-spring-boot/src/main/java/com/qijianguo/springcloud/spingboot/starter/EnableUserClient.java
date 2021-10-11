package com.qijianguo.springcloud.spingboot.starter;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;

import java.lang.annotation.*;

/**
 * 第六步：开启Starter自动构建
 * 通过注解的方式取代 `/MATE-INF/spring.factories` 配置
 * @author qijianguo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UserAutoConfigure.class) // 将配置文件注册到IoC容器中
public @interface EnableUserClient {
}
