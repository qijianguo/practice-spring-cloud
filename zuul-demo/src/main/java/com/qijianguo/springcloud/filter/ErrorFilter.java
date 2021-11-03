package com.qijianguo.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 错误拦截器
 * @author qijianguo
 */
public class ErrorFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Throwable throwable = currentContext.getThrowable();
        logger.error("Filter Error: {}", throwable.getCause().getMessage());
        return null;
    }
}
