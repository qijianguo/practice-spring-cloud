package com.qijianguo.springcloud.config;

import com.qijianguo.springcloud.filter.ErrorFilter;
import com.qijianguo.springcloud.filter.IpFilter;
import com.qijianguo.springcloud.filter.SignFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 * @author qijianguo
 */
@Configuration
public class FilterConfig {

    /**
     * IP过滤器配置
     * @return
     */
    @Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }

    @Bean
    public SignFilter signFilter() {
        return new SignFilter();
    }

    //@Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
}
