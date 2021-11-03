package com.qijianguo.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.*;

/**
 * 黑名单拦截器
 * @author qijianguo
 */
public class IpFilter extends ZuulFilter {

    private List<String> blackList = new ArrayList<>(Arrays.asList("127.0.0.1"));

    /**
     * 过滤器类型：pre，route，post，error
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，值越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String ip = getIp(request);
        if (StringUtils.isNotBlank(ip) && blackList.contains(ip)) {
            // 设置不转发请求到后端服务
            ctx.setSendZuulResponse(false);
            // 设置标识，在后续的Filter#shouldFilter()中做判断
            // 如果为true则后面的拦截器继续执行，否则不执行
            ctx.set("isSuccess", false);
            // FIXME
//            Map<String, Object> result = new HashMap<>();
//            result.put("code", 500);
//            result.put("message", "ip no permission!");
            String result = "{\"code\": 500, \"message\": \"no permission!\"}";
            ctx.setResponseBody(result);
            ctx.getResponse().setContentType("application/json;charset=utf-8");
        }
        // Filter之间传递消息,优先级高的拦截器向优先级低的拦截器传递消息
        ctx.set("msg", "hhhhhhhhhhhhhhhhhhh");
        return null;
    }

    private String getIp(HttpServletRequest request) {
        // 模拟拦截器异常
        //int i = 1/0;

        // TODO
//        return "127.0.0.1";
        return null;
    }
}
