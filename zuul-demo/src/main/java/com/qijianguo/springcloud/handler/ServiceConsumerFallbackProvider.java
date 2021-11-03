package com.qijianguo.springcloud.handler;

import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Zuul回退机制
 * @author qijianguo
 */
@Component
public class ServiceConsumerFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        // “*”表示对所有<服务>执行回退操作,
        // 如果只回退某个，则返回要返回的服务名称，服务名称必须在注册中心注册过
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                if (cause != null) {
                    RequestContext ctx = RequestContext.getCurrentContext();
                    //
                    String responseData = "{\"code\": 500, \"message\": \"服务器内部错误!\"}";
                    return new ByteArrayInputStream(responseData.getBytes());
                }
                return null;
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mediaType);
                return headers;
            }
        };
    }
}
