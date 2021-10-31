package com.qijianguo.springcloud.hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyHystrixCollapser extends HystrixCollapser<List<String>, String, String> {

    private final String name;

    public MyHystrixCollapser(String name) {
        this.name = name;
    }

    @Override
    public String getRequestArgument() {
        return this.name;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, String>> collapsedRequests) {
        return new BatchCommand(collapsedRequests);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, String>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<String, String> request : collapsedRequests) {
            request.setResponse(batchResponse.get(count++));
        }
    }

    /**
     * 执行请求
     */
    public static final class BatchCommand extends HystrixCommand<List<String>> {

        private final Collection<CollapsedRequest<String, String>> requests;

        protected BatchCommand(Collection<CollapsedRequest<String, String>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey"))
            );
            this.requests = requests;
        }

        /**
         * 执行所有请求
         * @return
         * @throws Exception
         */
        @Override
        protected List<String> run() throws Exception {
            System.out.println("执行真正的请求");
            List<String> response = new ArrayList<>();
            requests.forEach(request -> {
                response.add("返回结果：" + request.getArgument());
            });
            return response;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> aaa = new MyHystrixCollapser("aaa").queue();
        Future<String> bbb = new MyHystrixCollapser("bbb").queue();

        String s = aaa.get();
        String s1 = bbb.get();
        System.out.println(s + s1);
        context.close();
    }

}
