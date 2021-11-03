package com.qijianguo.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 签名拦截器
 * @author qijianguo
 */
public class SignFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Object isSuccess = currentContext.get("isSuccess");
        if (isSuccess instanceof Boolean) {
            if (!(boolean)isSuccess) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // TODO sign verify
        return null;
    }
}
